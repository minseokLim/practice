package com.minseoklim.designpattern.observer

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

class StatusCheckerTest {

    @Test
    fun check() {
        // given
        val statusChecker = StatusChecker()
        val statusSmsSender = StatusSmsSender()
        val statusEmailSender = StatusEmailSender()
        statusChecker.add(statusSmsSender)
        statusChecker.add(statusEmailSender)

        // when, then
        assertDoesNotThrow { statusChecker.check() }
    }
}
