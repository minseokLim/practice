package com.minseoklim.toyproject2024.chat.application

import com.minseoklim.toyproject2024.chat.domain.model.ChatRoom
import com.minseoklim.toyproject2024.chat.domain.repository.ChatRoomRepository
import com.minseoklim.toyproject2024.chat.dto.application.ChatRoomDto
import com.minseoklim.toyproject2024.chat.dto.application.MakeChatRoomOutput
import com.minseoklim.toyproject2024.member.application.QueryMemberService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class MakeChatRoomService(
    private val chatRoomRepository: ChatRoomRepository,
    private val queryMemberService: QueryMemberService,
    private val chatRoomNotifier: ChatRoomNotifier
) {
    fun make(
        creatorId: Int,
        memberIds: List<Int>
    ): MakeChatRoomOutput {
        val chatRoom = chatRoomRepository.save(ChatRoom(memberIds = memberIds, creatorId = creatorId))
        val members = queryMemberService.findAllByIds(chatRoom.getMemberIds())
        val memberIdToName = members.associate { it.id to it.name }

        chatRoomNotifier.notify(
            chatRoom.getMemberIds(),
            ChatRoomDto.of(chatRoom, memberIdToName)
        )

        return MakeChatRoomOutput.of(chatRoom, memberIdToName)
    }
}
