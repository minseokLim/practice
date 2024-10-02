package com.minseoklim.toyproject2024.chat.application

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
    private val messageRepository: MessageRepository
) {
    fun list(memberId: Int): List<QueryChatRoomOutput> {
        val chatRooms = chatRoomRepository.findAllByMemberId(memberId)
        if (chatRooms.isEmpty()) {
            return emptyList()
        }

        val members = getMembers(chatRooms)
        val lastMessages = getLastMessages(chatRooms)

        return chatRooms.map { QueryChatRoomOutput.of(it, members, lastMessages) }
    }

    private fun getMembers(chatRooms: List<ChatRoom>): List<QueryMemberOutput> {
        val memberIds = chatRooms.flatMap { it.chatRoomMembers.getMemberIds() }.toSet()
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
}
