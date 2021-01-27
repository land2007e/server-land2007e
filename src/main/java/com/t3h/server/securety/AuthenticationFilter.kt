package com.t3h.server.securety

import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


open class AuthenticationFilter : UsernamePasswordAuthenticationFilter{
    private val authen: AuthenticationManager

    constructor(authen: AuthenticationManager) {
        this.authen = authen
    }

    override fun attemptAuthentication(request: HttpServletRequest?, response: HttpServletResponse?): Authentication {
        return super.attemptAuthentication(request, response)
    }

    override fun successfulAuthentication(request: HttpServletRequest?, response: HttpServletResponse?, chain: FilterChain?, authResult: Authentication?) {
        super.successfulAuthentication(request, response, chain, authResult)
    }
}