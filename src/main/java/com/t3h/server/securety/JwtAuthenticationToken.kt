package com.t3h.server.securety

import org.springframework.security.authentication.AbstractAuthenticationToken
import org.springframework.security.core.GrantedAuthority

class JwtAuthenticationToken : AbstractAuthenticationToken {
    private var userContext: UserContext

    constructor(token: String?) : super(null) {
        userContext = UserContext()
        userContext.token = token!!
    }
    constructor(userContext: UserContext) : super(null) {
        this.userContext = userContext
        userContext.token = ""
    }
    constructor(userContext: UserContext, authorities: Collection<GrantedAuthority?>?) : super(authorities) {
        this.userContext = userContext
    }

    override fun getCredentials(): UserContext {
        return userContext
    }

    override fun getPrincipal(): Any? {
        return null
    }
}