package com.minseoklim.toyproject2024.chat.domain.repository

import com.minseoklim.toyproject2024.chat.domain.model.ChatRoom
import org.springframework.data.jpa.repository.JpaRepository

interface ChatRoomRepository : JpaRepository<ChatRoom, Long>
