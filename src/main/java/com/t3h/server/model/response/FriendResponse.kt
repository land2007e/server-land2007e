package com.t3h.server.model.response

import javax.persistence.Entity
import javax.persistence.Id

@Entity
class FriendResponse{
    @Id
    var friendId=0
    var username=""
    var firstName=""
    var lastName=""
    var avatar=""
    var status=""
}