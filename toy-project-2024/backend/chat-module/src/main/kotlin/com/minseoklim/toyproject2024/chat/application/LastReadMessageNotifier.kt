package com.minseoklim.toyproject2024.chat.application

import com.minseoklim.toyproject2024.chat.dto.application.LastReadMessageDto

interface LastReadMessageNotifier {
    fun notify(
        memberIds: Collection<Int>,
        lastReadMessage: LastReadMessageDto
    )
}
