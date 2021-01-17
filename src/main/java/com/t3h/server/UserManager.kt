package com.t3h.server

import com.t3h.server.model.database.UserProfile
import com.t3h.server.model.request.RegisterRequest
import com.t3h.server.model.response.CommonResponse
import com.t3h.server.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository

@Component
open class UserManager{
    @Autowired
    private lateinit var userRepository: UserRepository
    fun register(
            request:RegisterRequest
    ):CommonResponse{
        var user = userRepository.findOnByUsername(request.username)
        if (user != null){
            return CommonResponse("Username existed", 1)
        }
        var userProfile = UserProfile()
        userProfile.avatar=request.avatar
        userProfile.username=request.username
        userProfile.firstName=request.firstName
        userProfile.lastName=request.lastName
        userProfile.sex=request.sex
        userProfile.password=request.password
        userRepository.save(userProfile)
        return CommonResponse(userProfile)
    }
}