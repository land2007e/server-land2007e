package com.t3h.server.securety

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.security.web.authentication.AuthenticationFailureHandler
import kotlin.jvm.Throws
import java.io.IOException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.security.core.AuthenticationException

class TokenAuthenticationFailureHandler(val objectMapper: ObjectMapper) : AuthenticationFailureHandler {
    @Throws(IOException::class)
    override fun onAuthenticationFailure(
            request: HttpServletRequest, response: HttpServletResponse, e: AuthenticationException
    ) {
        response.status = HttpStatus.UNAUTHORIZED.value()
        val ex = ObjectException(
               message =  "User not exist"
        )
//        error.setTime(LocalDateTime.now())
//        error.setPath(request.servletPath)
        objectMapper.writeValue(response.writer, ex)
    }

}