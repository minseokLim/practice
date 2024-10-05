package com.minseoklim.toyproject2024.chat.application

import com.minseoklim.toyproject2024.chat.domain.repository.ChatRoomRepository
import com.minseoklim.toyproject2024.chat.dto.application.UpdateLastReadMessageInput
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class UpdateLastReadMessageService(
    private val chatRoomRepository: ChatRoomRepository
) {
    fun update(input: UpdateLastReadMessageInput) {
        val chatRoom = ChatServiceHelper.getChatRoom(chatRoomRepository, input.chatRoomId)
        val chatRoomMember = chatRoom.chatRoomMembers.getChatRoomMembers().first { it.memberId == input.memberId }
        chatRoomMember.updateLastReadMessageId(input.lastReadMessageId)
    }
}
