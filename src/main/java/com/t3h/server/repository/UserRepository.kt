package com.t3h.server.repository

import com.t3h.server.model.database.UserProfile
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

//tuong tac voi database
@Repository
open interface UserRepository:
        JpaRepository<UserProfile, Int>{

    @Query(nativeQuery = true,value = "SELECT * FROM user_profile " +
            "WHERE username = :username LIMIT 1")
    fun findOnByUsername(
            @Param(value = "username") username:String
    ):UserProfile?
}