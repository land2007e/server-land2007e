package com.t3h.server.model

class MessageContent {
    var receiverId = 0
    var senderId = 0
    var value = ""
    constructor()
    constructor(receiverId:Int, senderId:Int,
                value:String){
        this.receiverId = receiverId
        this.senderId = senderId
        this.value = value
    }
}