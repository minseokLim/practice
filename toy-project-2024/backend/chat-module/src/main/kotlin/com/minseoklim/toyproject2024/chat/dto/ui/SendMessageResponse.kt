package com.minseoklim.toyproject2024.chat.dto.ui

import com.minseoklim.toyproject2024.chat.dto.application.SendMessageOutput
import java.time.LocalDateTime

class SendMessageResponse private constructor(
    val id: Long,
    val content: String,
    val chatRoomId: Long,
    val member: MemberResponse,
    val isDeleted: Boolean,
    val createdDateTime: LocalDateTime
) {
    companion object {
        fun from(output: SendMessageOutput): SendMessageResponse {
            return SendMessageResponse(
                id = output.id,
                content = output.content,
                chatRoomId = output.chatRoomId,
                member = MemberResponse.from(output.member),
                isDeleted = output.isDeleted,
                createdDateTime = output.createdDateTime
            )
        }
    }

    data class MemberResponse private constructor(
        val id: Long,
        val name: String
    ) {
        companion object {
            fun from(output: SendMessageOutput.MemberOutput): MemberResponse {
                return MemberResponse(
                    id = output.id,
                    name = output.name
                )
            }
        }
    }
}
