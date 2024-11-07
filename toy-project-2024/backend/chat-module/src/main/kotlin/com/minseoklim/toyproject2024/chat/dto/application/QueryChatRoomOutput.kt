package com.minseoklim.toyproject2024.chat.dto.application

import com.minseoklim.toyproject2024.chat.domain.model.ChatRoom
import com.minseoklim.toyproject2024.chat.domain.model.Message
import com.minseoklim.toyproject2024.common.util.TextEncryptUtil
import java.time.LocalDateTime

data class QueryChatRoomOutput private constructor(
    val id: Long,
    val members: List<MemberOutput>,
    val lastMessage: MessageOutput?,
    val unreadMessageCount: Int
) {
    companion object {
        fun of(
            chatRoom: ChatRoom,
            memberIdToName: Map<Long, String>,
            messageIdToMessage: Map<Long, Message>,
            chatRoomIdToUnreadMessageCount: Map<Long, Int>
        ): QueryChatRoomOutput {
            val lastMessage = chatRoom.lastMessageId?.let { messageIdToMessage.getValue(it) }
            val chatRoomMembers = chatRoom.chatRoomMembers.getChatRoomMembers().map {
                MemberOutput(it.memberId, memberIdToName.getValue(it.memberId), it.lastReadMessageId)
            }

            return QueryChatRoomOutput(
                id = checkNotNull(chatRoom.id),
                members = chatRoomMembers,
                lastMessage = lastMessage?.let { MessageOutput.from(it) },
                unreadMessageCount = chatRoomIdToUnreadMessageCount[chatRoom.id] ?: 0
            )
        }
    }

    data class MemberOutput(
        val id: Long,
        val name: String,
        val lastReadMessageId: Long?
    )

    data class MessageOutput private constructor(
        val id: Long,
        val content: String,
        val memberId: Long,
        val isDeleted: Boolean,
        val createdDateTime: LocalDateTime
    ) {
        companion object {
            fun from(message: Message): MessageOutput {
                return MessageOutput(
                    id = checkNotNull(message.id),
                    content = TextEncryptUtil.decrypt(message.encryptedContent),
                    memberId = message.memberId,
                    isDeleted = message.isDeleted,
                    createdDateTime = message.createdDateTime
                )
            }
        }
    }
}
