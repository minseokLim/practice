package com.minseoklim.toyproject2024.auth.domain.model

import com.minseoklim.toyproject2024.test.util.TestUtil
import org.junit.jupiter.api.Test
import org.springframework.test.util.ReflectionTestUtils

class LoginHistoryTest {
    @Test
    fun equalsAndHashCode() {
        // given
        val loginHistory1 = LoginHistory(
            memberId = 1,
            tokenId = "tokenId",
            clientIp = "clientIp",
            userAgent = "userAgent"
        )
        val loginHistory2 = LoginHistory(
            memberId = 1,
            tokenId = "tokenId",
            clientIp = "clientIp",
            userAgent = "userAgent"
        )
        val loginHistory3 = LoginHistory(
            memberId = 1,
            tokenId = "tokenId",
            clientIp = "clientIp",
            userAgent = "userAgent"
        )
        ReflectionTestUtils.setField(loginHistory1, "id", 1)
        ReflectionTestUtils.setField(loginHistory2, "id", 1)

        // when, then
        TestUtil.testEqualsAndHashCode(loginHistory1, loginHistory2, loginHistory3)
    }
}
