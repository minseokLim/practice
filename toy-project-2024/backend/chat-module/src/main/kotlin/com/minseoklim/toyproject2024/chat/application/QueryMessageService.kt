package com.minseoklim.toyproject2024.chat.application

import com.minseoklim.toyproject2024.chat.domain.repository.ChatRoomRepository
import com.minseoklim.toyproject2024.chat.domain.repository.MessageRepository
import com.minseoklim.toyproject2024.chat.dto.application.QueryMessageOutput
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class QueryMessageService(
    private val chatRoomRepository: ChatRoomRepository,
    private val messageRepository: MessageRepository
) {
    fun list(
        memberId: Int,
        chatRoomId: Long,
        cursorId: Long,
        size: Int
    ): List<QueryMessageOutput> {
        val chatRoom = ChatServiceHelper.getChatRoom(chatRoomRepository, chatRoomId)
        chatRoom.checkAuthority(memberId)

        val messages = messageRepository.findAllByIdLessThanAndChatRoomIdOrderByIdDesc(
            cursorId = cursorId,
            chatRoomId = chatRoomId,
            limit = PageRequest.of(0, size)
        )
        return messages.map { QueryMessageOutput.from(it) }
    }
}
