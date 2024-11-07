package com.minseoklim.toyproject2024.notification.application

import com.minseoklim.toyproject2024.common.domain.type.ErrorCode
import com.minseoklim.toyproject2024.common.exception.BadRequestException
import com.minseoklim.toyproject2024.notification.domain.repository.NotificationRepository
import com.minseoklim.toyproject2024.notification.domain.repository.PushTokenRepository
import com.minseoklim.toyproject2024.notification.dto.application.SendNotificationInput
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class SendNotificationService(
    private val pushTokenRepository: PushTokenRepository,
    private val notificationRepository: NotificationRepository,
    private val pushApi: PushApi
) {
    fun send(
        memberId: Long,
        input: SendNotificationInput
    ) {
        val token = pushTokenRepository.findByMemberId(memberId)
            ?: throw BadRequestException(ErrorCode.PUSH_TOKEN_NOT_FOUND)
        notificationRepository.save(input.toEntity(memberId))

        val pushRequest = PushApi.PushRequest(
            title = input.title,
            body = input.body,
            imageUrl = input.imageUrl,
            token = token.token
        )
        pushApi.sendPush(pushRequest)
    }
}
