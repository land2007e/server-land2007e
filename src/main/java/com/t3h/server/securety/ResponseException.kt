package com.t3h.server.securety

class ResponseException : Exception {
    val obj: ObjectException
    val httpStatus:Int
    constructor(httpStatus:Int, obj: ObjectException) : super(obj.message) {
        this.obj = obj
        this.httpStatus=httpStatus

    }
}