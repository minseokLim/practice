package com.minseoklim.toyproject2024.chat.dto.application

import com.minseoklim.toyproject2024.chat.domain.model.ChatRoom
import com.minseoklim.toyproject2024.member.dto.application.QueryMemberOutput

data class MakeChatRoomOutput private constructor(
    val id: Long,
    val members: List<MemberOutput>,
    val creator: MemberOutput
) {
    companion object {
        fun of(
            chatRoom: ChatRoom,
            members: Collection<QueryMemberOutput>
        ): MakeChatRoomOutput {
            val memberIdToName = members.associate { it.id to it.name }
            return MakeChatRoomOutput(
                id = checkNotNull(chatRoom.id),
                members = chatRoom.getMemberIds().map { MemberOutput(it, memberIdToName.getValue(it)) },
                creator = MemberOutput(chatRoom.creatorId, memberIdToName.getValue(chatRoom.creatorId))
            )
        }
    }

    data class MemberOutput(
        val id: Int,
        val name: String
    )
}
