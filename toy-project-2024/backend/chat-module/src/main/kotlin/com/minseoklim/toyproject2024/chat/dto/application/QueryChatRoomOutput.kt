package com.minseoklim.toyproject2024.chat.dto.application

import com.minseoklim.toyproject2024.chat.domain.mapper.UnreadMessageCount
import com.minseoklim.toyproject2024.chat.domain.model.ChatRoom
import com.minseoklim.toyproject2024.chat.domain.model.Message
import com.minseoklim.toyproject2024.common.util.TextEncryptUtil
import com.minseoklim.toyproject2024.member.dto.application.QueryMemberOutput
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
            members: Collection<QueryMemberOutput>,
            lastMessages: Collection<Message>,
            unreadMessageCounts: Collection<UnreadMessageCount>
        ): QueryChatRoomOutput {
            val memberIdToName = members.associate { it.id to it.name }
            val messageIdToMessage = lastMessages.associateBy { it.id }
            val chatRoomIdToUnreadMessageCount =
                unreadMessageCounts.associate { it.chatRoomId to it.unreadMessageCount }

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
        val id: Int,
        val name: String,
        val lastReadMessageId: Long?
    )

    data class MessageOutput private constructor(
        val id: Long,
        val content: String,
        val memberId: Int,
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
