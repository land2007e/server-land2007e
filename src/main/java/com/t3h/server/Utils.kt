package com.t3h.server

import com.t3h.server.securety.JwtAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import java.security.Security

object Utils {
    fun getUserLogin():Int{
        return (SecurityContextHolder.getContext()
                .authentication as JwtAuthenticationToken)
                .credentials.id
    }
}