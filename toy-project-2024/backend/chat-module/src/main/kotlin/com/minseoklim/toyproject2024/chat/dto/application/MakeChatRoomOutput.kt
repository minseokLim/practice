package com.minseoklim.toyproject2024.chat.dto.application

import com.minseoklim.toyproject2024.chat.domain.model.ChatRoom

data class MakeChatRoomOutput private constructor(
    val id: Long,
    val members: List<MemberOutput>,
    val creator: MemberOutput
) {
    companion object {
        fun of(
            chatRoom: ChatRoom,
            memberIdToName: Map<Int, String>
        ): MakeChatRoomOutput {
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
