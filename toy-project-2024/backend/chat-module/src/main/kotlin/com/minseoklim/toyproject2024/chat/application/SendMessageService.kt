package com.minseoklim.toyproject2024.chat.application

import com.minseoklim.toyproject2024.chat.domain.repository.MessageRepository
import com.minseoklim.toyproject2024.chat.domain.repository.RoomRepository
import com.minseoklim.toyproject2024.chat.dto.application.MessageDto
import com.minseoklim.toyproject2024.chat.dto.application.SendMessageInput
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class SendMessageService(
    private val messageRepository: MessageRepository,
    private val roomRepository: RoomRepository,
    private val messageNotifier: MessageNotifier
) {
    fun send(
        memberId: Int,
        input: SendMessageInput
    ) {
        val message = messageRepository.save(input.toEntity(memberId))
        val room = ChatServiceHelper.getRoom(roomRepository, message.roomId)

        messageNotifier.notify(room.getMemberIds(), MessageDto.from(message))
    }
}
