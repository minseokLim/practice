package com.minseoklim.toyproject2024.chat.application

import com.minseoklim.toyproject2024.chat.domain.mapper.ChatMapper
import com.minseoklim.toyproject2024.chat.dto.application.UpdateLastReadMessageInput
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class UpdateLastReadMessageService(
    private val chatMapper: ChatMapper
) {
    fun update(input: UpdateLastReadMessageInput) {
        chatMapper.updateLastReadMessageId(input.toMap())
    }
}
