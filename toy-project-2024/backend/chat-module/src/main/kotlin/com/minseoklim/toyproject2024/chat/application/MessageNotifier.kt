package com.minseoklim.toyproject2024.chat.application

import com.minseoklim.toyproject2024.chat.dto.application.MessageDto

interface MessageNotifier {
    fun notify(
        memberIds: Collection<Long>,
        message: MessageDto
    )
}
