package com.minseoklim.toyproject2024.chat.application

import com.minseoklim.toyproject2024.chat.domain.repository.ChatRoomRepository
import com.minseoklim.toyproject2024.chat.dto.application.LastReadMessageDto
import com.minseoklim.toyproject2024.chat.dto.application.UpdateLastReadMessageInput
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class UpdateLastReadMessageService(
    private val chatRoomRepository: ChatRoomRepository,
    private val lastReadMessageNotifier: LastReadMessageNotifier
) {
    fun update(input: UpdateLastReadMessageInput) {
        val chatRoom = ChatServiceHelper.getChatRoom(chatRoomRepository, input.chatRoomId)
        val chatRoomMember = chatRoom.chatRoomMembers.getChatRoomMembers().first { it.memberId == input.memberId }
        chatRoomMember.updateLastReadMessageId(input.lastReadMessageId)

        val notifyTargetMemberIds = chatRoom.chatRoomMembers.getMemberIds().filter { it != input.memberId }
        lastReadMessageNotifier.notify(
            notifyTargetMemberIds,
            LastReadMessageDto(input.chatRoomId, input.memberId, input.lastReadMessageId)
        )
    }
}
