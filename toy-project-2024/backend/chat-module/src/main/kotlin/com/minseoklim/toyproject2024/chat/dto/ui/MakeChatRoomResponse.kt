package com.minseoklim.toyproject2024.chat.dto.ui

import com.minseoklim.toyproject2024.chat.dto.application.MakeChatRoomOutput

data class MakeChatRoomResponse private constructor(
    val id: Long,
    val members: List<MemberResponse>,
    val creator: MemberResponse
) {
    companion object {
        fun from(output: MakeChatRoomOutput): MakeChatRoomResponse {
            return MakeChatRoomResponse(
                id = output.id,
                members = output.members.map { MemberResponse.from(it) },
                creator = MemberResponse.from(output.creator)
            )
        }
    }

    data class MemberResponse private constructor(
        val id: Int,
        val name: String
    ) {
        companion object {
            fun from(output: MakeChatRoomOutput.MemberOutput): MemberResponse {
                return MemberResponse(
                    id = output.id,
                    name = output.name
                )
            }
        }
    }
}
