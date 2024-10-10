package com.minseoklim.toyproject2024.chat.domain.repository

import com.minseoklim.toyproject2024.chat.domain.model.Message
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface MessageRepository : JpaRepository<Message, Long> {
    fun findAllByIdLessThanAndChatRoomIdOrderByIdDesc(
        cursorId: Long,
        chatRoomId: Long,
        limit: Pageable
    ): List<Message>
}
