package com.minseoklim.toyproject2024.auth.event

import com.minseoklim.toyproject2024.auth.application.LogoutService
import com.minseoklim.toyproject2024.member.event.MemberDeletedEvent
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import org.springframework.transaction.event.TransactionPhase
import org.springframework.transaction.event.TransactionalEventListener

@Component
class MemberDeletedEventHandler(
    private val logoutService: LogoutService
) {
    @Async
    @TransactionalEventListener(classes = [MemberDeletedEvent::class], phase = TransactionPhase.AFTER_COMMIT)
    fun handle(event: MemberDeletedEvent) {
        logoutService.logoutAll(event.memberId)
    }
}
