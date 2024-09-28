package com.minseoklim.toyproject2024.chat.application

import com.minseoklim.toyproject2024.chat.domain.model.Room
import com.minseoklim.toyproject2024.chat.domain.repository.RoomRepository
import com.minseoklim.toyproject2024.chat.dto.application.MakeRoomOutput
import com.minseoklim.toyproject2024.chat.dto.application.RoomDto
import com.minseoklim.toyproject2024.member.application.QueryMemberService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class MakeRoomService(
    private val roomRepository: RoomRepository,
    private val queryMemberService: QueryMemberService,
    private val roomNotifier: RoomNotifier
) {
    fun make(
        creatorId: Int,
        memberIds: List<Int>
    ): MakeRoomOutput {
        val room = roomRepository.save(Room(memberIds = memberIds, creatorId = creatorId))
        val members = queryMemberService.findAllByIds(room.getMemberIds())
        roomNotifier.notify(room.getMemberIds(), RoomDto.of(room, members))

        return MakeRoomOutput.from(room)
    }
}
