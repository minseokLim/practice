package com.minseoklim.toyproject2024.chat.dto.ui

import com.minseoklim.toyproject2024.chat.dto.application.QueryMessageOutput

data class QueryMessageResponse private constructor(
    val id: Long,
    val content: String,
    val chatRoomId: Long,
    val memberId: Int,
    val isDeleted: Boolean
) {
    companion object {
        fun from(output: QueryMessageOutput): QueryMessageResponse {
            return QueryMessageResponse(
                id = output.id,
                content = output.content,
                chatRoomId = output.chatRoomId,
                memberId = output.memberId,
                isDeleted = output.isDeleted
            )
        }
    }
}
