package com.minseoklim.toyproject2024.chat.application

import com.minseoklim.toyproject2024.chat.domain.model.ChatRoom
import com.minseoklim.toyproject2024.chat.domain.repository.ChatRoomRepository
import com.minseoklim.toyproject2024.common.exception.NotFoundException

object ChatServiceHelper {
    fun getChatRoom(
        chatRoomRepository: ChatRoomRepository,
        chatRoomId: Long
    ): ChatRoom {
        return chatRoomRepository.findById(chatRoomId)
            .orElseThrow { NotFoundException("ROOM_NOT_FOUND", "찾을 수 없는 채팅 방입니다.") }
    }
}
