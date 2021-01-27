package com.t3h.server.securety

import com.t3h.server.model.database.UserProfile
import com.t3h.server.repository.UserRepository
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.context.SecurityContext
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter
import org.springframework.security.web.util.matcher.RequestMatcher
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


open class AuthorizationFilter : AbstractAuthenticationProcessingFilter {
    private val userRepository: UserRepository

    constructor(requestMatch: RequestMatcher, userRepository: UserRepository) :
            super(requestMatch) {
        this.userRepository = userRepository
    }

    //verify token
    @Throws(ServletException::class, ServletException::class)
    override fun attemptAuthentication(request: HttpServletRequest, response: HttpServletResponse): Authentication {
        var token = request.getHeader("Authorization")
        // token = "Bearer token"
        token = token.substring("Bearer ".length)
        val user = authenticate(request, token)
        val au = UsernamePasswordAuthenticationToken(user, null)

       return au
    }

    //verify token


    private fun authenticate(request: HttpServletRequest, token: String):
            UserProfile {
        //phan tich token
//        123a@: SigningKey
        val user: Claims = Jwts.parser()
                .setSigningKey("123a@")
                .parseClaimsJws(token)
                .getBody()
        val username = user.subject
        val userRe = userRepository.findOnByUsername(username)
        if (userRe == null) {
            throw ResponseException(
                    HttpStatus.BAD_REQUEST.value(),
                    ObjectException(message = "User not exist")
            )
        }
        return userRe

    }
    @Throws(ServletException::class, ServletException::class)
    override fun successfulAuthentication(request: HttpServletRequest?, response: HttpServletResponse?, chain: FilterChain?,
                                          authResult: Authentication) {

        val auth = authResult as UsernamePasswordAuthenticationToken
        val userProfile = auth.principal as UserProfile
        val userContext = UserContext()
        userContext.id = userProfile.id
        userContext.token = userProfile.token
        userContext.username = userProfile.username

        val context: SecurityContext = SecurityContextHolder.createEmptyContext()
        context.setAuthentication(JwtAuthenticationToken(userContext))
        SecurityContextHolder.setContext(context)
        if (!response!!.isCommitted) {
            chain!!.doFilter(request, response)
        }
    }

    override fun unsuccessfulAuthentication(request: HttpServletRequest, response: HttpServletResponse, failed: AuthenticationException?) {
        SecurityContextHolder.clearContext();
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
        failureHandler.onAuthenticationFailure(request, response, failed)
    }

}