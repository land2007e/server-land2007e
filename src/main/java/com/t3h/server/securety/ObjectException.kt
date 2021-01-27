package com.t3h.server.securety

data class ObjectException (
        val status: Int = 1,
        val message:String,
        var uri:String?=null
)