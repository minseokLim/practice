package com.minseoklim.toyproject2024.chat.application

import com.minseoklim.toyproject2024.chat.domain.model.Room
import com.minseoklim.toyproject2024.chat.domain.repository.RoomRepository
import com.minseoklim.toyproject2024.common.exception.NotFoundException

object ChatServiceHelper {
    fun getRoom(
        roomRepository: RoomRepository,
        roomId: Long
    ): Room {
        return roomRepository.findById(roomId)
            .orElseThrow { NotFoundException("ROOM_NOT_FOUND", "찾을 수 없는 채팅 방입니다.") }
    }
}
