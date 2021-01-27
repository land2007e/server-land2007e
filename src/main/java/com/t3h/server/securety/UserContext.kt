package com.t3h.server.securety

class UserContext {
    var id = 0
    var token: String=""
    var username: String=""

    constructor() {}
    constructor(token: String) {
        this.token = token
    }
}