package com.t3h.server.repository

import com.t3h.server.model.database.Message
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MessageRepository:JpaRepository<Message, Int>{

}