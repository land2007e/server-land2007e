package com.t3h.server.controller

import com.t3h.server.manager.UserManager
import com.t3h.server.model.request.RegisterRequest
import com.t3h.server.model.request.RequestLogin
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
open class UserController {

    private lateinit var manager: UserManager
    @Autowired
    constructor(manager: UserManager) {
        this.manager = manager
    }

    @PostMapping("/api/register")
    fun register(
            @RequestBody request:RegisterRequest
    ) : Any{

        return manager.register(request)
    }

    @PostMapping("/auth/login")
    fun login(
            @RequestBody request:RequestLogin
    ):  Any{
        return manager.login(request)
    }

    @GetMapping("/api/friends/{userId}")
    fun getAllFriend(
            @PathVariable("userId")userId:Int
    ):Any{
        return manager.getAllFriend(userId)
    }
}