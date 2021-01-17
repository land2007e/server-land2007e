package com.t3h.server.model.database

import javax.persistence.*

@Entity
open class UserProfile{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    var id = 0
    var username=""
    var password=""
    @Column(name = "first_name")
    var firstName=""
    @Column(name = "last_name")
    var lastName=""
    var avatar:String?=null
    var sex = "FEMALE"

}