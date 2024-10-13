package com.minseoklim.toyproject2024.chat.application

import com.minseoklim.toyproject2024.chat.domain.mapper.ChatMapper
import com.minseoklim.toyproject2024.chat.domain.mapper.UnreadMessageCount
import com.minseoklim.toyproject2024.chat.domain.model.ChatRoom
import com.minseoklim.toyproject2024.chat.domain.model.Message
import com.minseoklim.toyproject2024.chat.domain.repository.ChatRoomRepository
import com.minseoklim.toyproject2024.chat.domain.repository.MessageRepository
import com.minseoklim.toyproject2024.chat.dto.application.QueryChatRoomOutput
import com.minseoklim.toyproject2024.member.application.QueryMemberService
import com.minseoklim.toyproject2024.member.dto.application.QueryMemberOutput
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class QueryChatRoomService(
    private val chatRoomRepository: ChatRoomRepository,
    private val queryMemberService: QueryMemberService,
    private val messageRepository: MessageRepository,
    private val chatMapper: ChatMapper
) {
    fun list(memberId: Int): List<QueryChatRoomOutput> {
        val chatRooms = chatRoomRepository.findAllByMemberId(memberId)
        if (chatRooms.isEmpty()) {
            return emptyList()
        }

        val members = getMembers(chatRooms)
        val lastMessages = getLastMessages(chatRooms)
        val unreadMessageCounts = getUnreadMessageCounts(chatRooms, memberId)

        val memberIdToName = members.associate { it.id to it.name }
        val messageIdToMessage = lastMessages.associateBy { checkNotNull(it.id) }
        val chatRoomIdToUnreadMessageCount = unreadMessageCounts.associate { it.chatRoomId to it.unreadMessageCount }

        return chatRooms.map {
            QueryChatRoomOutput.of(
                it,
                memberIdToName,
                messageIdToMessage,
                chatRoomIdToUnreadMessageCount
            )
        }
    }

    private fun getMembers(chatRooms: List<ChatRoom>): List<QueryMemberOutput> {
        val memberIds = chatRooms.flatMap { it.getMemberIds() }.toSet()
        return queryMemberService.findAllByIds(memberIds)
    }

    private fun getLastMessages(chatRooms: List<ChatRoom>): List<Message> {
        val lastMessageIds = chatRooms.mapNotNull { it.lastMessageId }
        return if (lastMessageIds.isEmpty()) {
            emptyList()
        } else {
            messageRepository.findAllById(lastMessageIds)
        }
    }

    private fun getUnreadMessageCounts(
        chatRooms: List<ChatRoom>,
        memberId: Int
    ): List<UnreadMessageCount> {
        val params = chatRooms.map { chatRoom ->
            mapOf(
                "chatRoomId" to chatRoom.id,
                "lastReadMessageId" to chatRoom.chatRoomMembers.getChatRoomMembers()
                    .first { it.memberId == memberId }.lastReadMessageId
            )
        }
        return chatMapper.selectUnreadMessageCounts(params)
    }
}
