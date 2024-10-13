package com.minseoklim.toyproject2024.chat.dto.ui

import com.minseoklim.toyproject2024.chat.dto.application.QueryMessageOutput
import java.time.LocalDateTime

data class QueryMessageResponse private constructor(
    val id: Long,
    val content: String,
    val chatRoomId: Long,
    val member: MemberResponse,
    val isDeleted: Boolean,
    val createdDateTime: LocalDateTime
) {
    companion object {
        fun from(output: QueryMessageOutput): QueryMessageResponse {
            return QueryMessageResponse(
                id = output.id,
                content = output.content,
                chatRoomId = output.chatRoomId,
                member = MemberResponse.from(output.member),
                isDeleted = output.isDeleted,
                createdDateTime = output.createdDateTime
            )
        }
    }

    data class MemberResponse(
        val id: Int,
        val name: String
    ) {
        companion object {
            fun from(output: QueryMessageOutput.MemberOutput): MemberResponse {
                return MemberResponse(
                    id = output.id,
                    name = output.name
                )
            }
        }
    }
}
