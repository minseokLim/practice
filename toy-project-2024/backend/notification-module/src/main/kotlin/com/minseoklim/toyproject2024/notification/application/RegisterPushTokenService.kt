package com.minseoklim.toyproject2024.notification.application

import com.minseoklim.toyproject2024.notification.domain.model.PushToken
import com.minseoklim.toyproject2024.notification.domain.repository.PushTokenRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class RegisterPushTokenService(
    private val pushTokenRepository: PushTokenRepository
) {
    fun register(
        memberId: Long,
        token: String
    ) {
        val pushToken = pushTokenRepository.findByToken(token)
        if (pushToken != null) {
            pushTokenRepository.delete(pushToken)
        }
        pushTokenRepository.save(PushToken(token = token, memberId = memberId))
    }
}
