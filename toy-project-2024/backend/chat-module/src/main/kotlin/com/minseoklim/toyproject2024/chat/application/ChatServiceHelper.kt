package com.minseoklim.toyproject2024.chat.application

import com.minseoklim.toyproject2024.chat.domain.model.ChatRoom
import com.minseoklim.toyproject2024.chat.domain.repository.ChatRoomRepository
import com.minseoklim.toyproject2024.common.domain.type.ErrorCode
import com.minseoklim.toyproject2024.common.exception.NotFoundException

object ChatServiceHelper {
    fun getChatRoom(
        chatRoomRepository: ChatRoomRepository,
        chatRoomId: Long
    ): ChatRoom {
        return chatRoomRepository.findById(chatRoomId)
            .orElseThrow { NotFoundException(ErrorCode.ROOM_NOT_FOUND) }
    }
}
