package com.minseoklim.toyproject2024.chat.application

import com.minseoklim.toyproject2024.chat.domain.repository.ChatRoomRepository
import com.minseoklim.toyproject2024.chat.domain.repository.MessageRepository
import com.minseoklim.toyproject2024.chat.dto.application.MessageDto
import com.minseoklim.toyproject2024.chat.dto.application.SendMessageInput
import com.minseoklim.toyproject2024.chat.dto.application.SendMessageOutput
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class SendMessageService(
    private val messageRepository: MessageRepository,
    private val chatRoomRepository: ChatRoomRepository,
    private val messageNotifier: MessageNotifier
) {
    fun send(
        memberId: Int,
        input: SendMessageInput
    ): SendMessageOutput {
        val message = messageRepository.save(input.toEntity(memberId))
        val chatRoom = ChatServiceHelper.getChatRoom(chatRoomRepository, message.chatRoomId)
        chatRoom.updateLastMessageId(checkNotNull(message.id))

        messageNotifier.notify(chatRoom.getMemberIds().filter { it != memberId }, MessageDto.from(message))

        return SendMessageOutput.from(message)
    }
}
