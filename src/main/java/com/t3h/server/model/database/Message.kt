package com.t3h.server.model.database

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Message{
    @Id
    var id = 0
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    var senderId=0
    var receiverId=0
    var status=""
    var content=""
}