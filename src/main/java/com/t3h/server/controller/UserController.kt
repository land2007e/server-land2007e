package com.t3h.server.controller

import com.t3h.server.UserManager
import com.t3h.server.model.request.RegisterRequest
import com.t3h.server.model.request.RequestLogin
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import javax.websocket.server.PathParam

@RestController
open class UserController {
    @Autowired
    private lateinit var manager:UserManager
    @PostMapping("/api/register")
    fun register(
            @RequestBody request:RegisterRequest
    ) : Any{

        return manager.register(request)
    }

    @PostMapping("/api/login")
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