package com.minseoklim.toyproject2024.common.util

import com.minseoklim.toyproject2024.common.util.ClientUtil.getClientIp
import com.minseoklim.toyproject2024.common.util.ClientUtil.getUserAgent
import io.mockk.every
import io.mockk.mockk
import jakarta.servlet.http.HttpServletRequest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ClientUtilTest {

    @Test
    fun getClientIp() {
        // given
        var request: HttpServletRequest = mockk()
        val expectedIp = "127.0.0.1"
        every { request.getHeader("X-Forwarded-For") } returns expectedIp

        // when
        val result1 = request.getClientIp()

        // then
        assertThat(result1).isEqualTo(expectedIp)

        // given
        request = mockk()
        every { request.getHeader("X-Forwarded-For") } returns null
        every { request.getHeader("X-Real-IP") } returns expectedIp

        // when
        val result2 = request.getClientIp()

        // then
        assertThat(result2).isEqualTo(expectedIp)

        // given
        request = mockk()
        every { request.getHeader("X-Forwarded-For") } returns "unknown"
        every { request.getHeader("X-Real-IP") } returns null
        every { request.remoteAddr } returns expectedIp

        // when
        val result3 = request.getClientIp()

        // then
        assertThat(result3).isEqualTo(expectedIp)

        // given
        request = mockk()
        every { request.getHeader("X-Forwarded-For") } returns ""
        every { request.getHeader("X-Real-IP") } returns "unknown"
        every { request.remoteAddr } returns expectedIp

        // when
        val result4 = request.getClientIp()

        // then
        assertThat(result4).isEqualTo(expectedIp)

        // given
        request = mockk()
        every { request.getHeader("X-Forwarded-For") } returns ""
        every { request.getHeader("X-Real-IP") } returns ""
        every { request.remoteAddr } returns expectedIp

        // when
        val result5 = request.getClientIp()

        // then
        assertThat(result5).isEqualTo(expectedIp)
    }

    @Test
    fun getUserAgent() {
        // given
        val request: HttpServletRequest = mockk()
        val expectedUserAgent = "Mozilla/5.0"
        every { request.getHeader("User-Agent") } returns expectedUserAgent

        // when
        val result = request.getUserAgent()

        // then
        assertThat(result).isEqualTo(expectedUserAgent)
    }
}
