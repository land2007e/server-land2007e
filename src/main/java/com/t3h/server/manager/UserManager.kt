package com.t3h.server.manager

import com.t3h.server.model.database.UserProfile
import com.t3h.server.model.request.RegisterRequest
import com.t3h.server.model.request.RequestLogin
import com.t3h.server.model.response.CommonResponse
import com.t3h.server.repository.FriendRepository
import com.t3h.server.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository
import java.util.*

@Component
open class UserManager {
    @Autowired
    private lateinit var userRepository: UserRepository

    @Autowired
    private lateinit var friendRepository: FriendRepository

    fun register(
            request: RegisterRequest
    ): Any {
        var user = userRepository.findOnByUsername(request.username)
        if (user != null) {
            return CommonResponse("Username existed", 1)
        }
        var userProfile = UserProfile()
        userProfile.avatar = request.avatar
        userProfile.username = request.username
        userProfile.firstName = request.firstName
        userProfile.lastName = request.lastName
        userProfile.sex = request.sex
        val encode = BCryptPasswordEncoder()
        userProfile.password = encode.encode(request.password)
        userRepository.save(userProfile)
        return CommonResponse(userProfile)
    }

    fun login(request: RequestLogin): Any {
        val user = userRepository.findOnByUsername(
                request.username
        )
        if (user == null) {
            return CommonResponse("username invalid", 1)
        }
        val end = BCryptPasswordEncoder()

        if (!end.matches(request.password, user.password)) {
            return CommonResponse("password invalid", 1)
        }
        user.token=getJWT(user)
        return return CommonResponse(user)
    }

    fun getAllFriend(userId: Int): Any {
        return CommonResponse(
                friendRepository.findAllFriend(
                        userId
                )
        )
    }

    fun getJWT(user:UserProfile):String{
//        val claims = Jwts.claims()
//                .setSubject(user.username)
//        claims.id = user.id.toString()
//        claims.set("username", user.username)
//        claims.set("avatar", user.avatar)
//        claims.set("firstName", user.firstName)
//        claims.set("lastName", user.lastName)
//
//        return Jwts.builder().setClaims(claims)
//                .signWith( SignatureAlgorithm.HS512, "123a@")
//                .setExpiration(Date(Date().time + 10*60*1000L))
//                .compact()
        return ""
    }
}