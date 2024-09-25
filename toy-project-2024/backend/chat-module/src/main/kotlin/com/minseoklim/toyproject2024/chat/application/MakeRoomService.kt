package com.minseoklim.toyproject2024.chat.application

import com.minseoklim.toyproject2024.chat.domain.model.Room
import com.minseoklim.toyproject2024.chat.domain.repository.RoomRepository
import com.minseoklim.toyproject2024.chat.dto.application.MakeRoomOutput
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class MakeRoomService(
    private val roomRepository: RoomRepository
) {
    fun make(
        creatorId: Int,
        memberIds: List<Int>
    ): MakeRoomOutput {
        val room = roomRepository.save(Room(memberIds = memberIds, creatorId = creatorId))
        return MakeRoomOutput.from(room)
    }
}
