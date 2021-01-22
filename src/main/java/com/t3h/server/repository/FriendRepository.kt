package com.t3h.server.repository

import com.t3h.server.model.response.FriendResponse
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
open interface FriendRepository : JpaRepository<FriendResponse, Int> {
    //    var friendId=0
//    var username=""
//    var firstName=""
//    var lastName=""
//    var avatar=""
//    var status=""
    @Query(nativeQuery = true,
            value = "SELECT " +
                    "user_profile.id as friend_id, " +
                    "username, " +
                    "first_name, " +
                    "last_name, " +
                    "avatar, " +
                    "status " +
                    "FROM friend join user_profile ON " +
                    "(friend.receiver_id = user_profile.id and friend.sender_id = :userId) " +
                    "OR (friend.sender_id = user_profile.id and friend.receiver_id = :userId) " +
                    "WHERE friend.status = 'accepted'"
    )
    fun findAllFriend(
            @Param("userId") userId: Int
    ): MutableList<FriendResponse>
}