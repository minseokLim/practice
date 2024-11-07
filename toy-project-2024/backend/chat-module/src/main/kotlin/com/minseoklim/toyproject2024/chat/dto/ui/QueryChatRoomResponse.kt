package com.minseoklim.toyproject2024.chat.dto.ui

import com.minseoklim.toyproject2024.chat.dto.application.QueryChatRoomOutput
import java.time.LocalDateTime

data class QueryChatRoomResponse private constructor(
    val id: Long,
    val members: List<MemberResponse>,
    val lastMessage: MessageResponse?,
    val unreadMessageCount: Int
) {
    companion object {
        fun from(output: QueryChatRoomOutput): QueryChatRoomResponse {
            return QueryChatRoomResponse(
                id = output.id,
                members = output.members.map { MemberResponse.from(it) },
                lastMessage = output.lastMessage?.let { MessageResponse.from(it) },
                unreadMessageCount = output.unreadMessageCount
            )
        }
    }

    data class MemberResponse private constructor(
        val id: Long,
        val name: String,
        val lastReadMessageId: Long?
    ) {
        companion object {
            fun from(output: QueryChatRoomOutput.MemberOutput): MemberResponse {
                return MemberResponse(
                    id = output.id,
                    name = output.name,
                    lastReadMessageId = output.lastReadMessageId
                )
            }
        }
    }

    data class MessageResponse private constructor(
        val id: Long,
        val content: String,
        val memberId: Long,
        val isDeleted: Boolean,
        val createdDateTime: LocalDateTime
    ) {
        companion object {
            fun from(output: QueryChatRoomOutput.MessageOutput): MessageResponse {
                return MessageResponse(
                    id = output.id,
                    content = output.content,
                    memberId = output.memberId,
                    isDeleted = output.isDeleted,
                    createdDateTime = output.createdDateTime
                )
            }
        }
    }
}
