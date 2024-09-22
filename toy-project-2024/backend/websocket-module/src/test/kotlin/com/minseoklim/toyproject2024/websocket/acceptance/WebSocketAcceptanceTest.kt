package com.minseoklim.toyproject2024.websocket.acceptance

import com.minseoklim.toyproject2024.auth.acceptance.AuthAcceptanceTestFixture.`로그인 요청`
import com.minseoklim.toyproject2024.auth.acceptance.AuthAcceptanceTestUtil.extractAccessToken
import com.minseoklim.toyproject2024.member.acceptance.MemberAcceptanceTestFixture.`회원 가입 요청`
import com.minseoklim.toyproject2024.test.AcceptanceTest
import com.minseoklim.toyproject2024.websocket.acceptance.WebSocketAcceptanceTestFixture.`웹소켓 연결 요청`
import com.minseoklim.toyproject2024.websocket.acceptance.WebSocketAcceptanceTestFixture.`웹소켓 연결 종료`
import com.minseoklim.toyproject2024.websocket.acceptance.WebSocketAcceptanceTestFixture.`웹소켓 연결됨`
import com.minseoklim.toyproject2024.websocket.acceptance.WebSocketAcceptanceTestFixture.`웹소켓 토큰 발급 요청`
import com.minseoklim.toyproject2024.websocket.acceptance.WebSocketAcceptanceTestFixture.`웹소켓 토큰 발급됨`
import org.junit.jupiter.api.Test

class WebSocketAcceptanceTest : AcceptanceTest() {
    @Test
    fun `웹소켓 서비스`() {
        // given
        val memberJoinRequest = mapOf(
            "loginId" to MEMBER_ID,
            "password" to PASSWORD,
            "name" to "testName",
            "email" to "test@test.com"
        )
        `회원 가입 요청`(memberJoinRequest)

        // given
        val loginRequest = mapOf(
            "loginId" to MEMBER_ID,
            "password" to PASSWORD
        )
        val loginResponse = `로그인 요청`(loginRequest)
        val accessToken = loginResponse.extractAccessToken()

        // when
        val tokenResponse = `웹소켓 토큰 발급 요청`(accessToken)

        // then
        `웹소켓 토큰 발급됨`(tokenResponse)

        // given
        val token: String = tokenResponse.jsonPath().get("token")

        // when
        val session = `웹소켓 연결 요청`(this.port, token)

        // then
        `웹소켓 연결됨`(session)

        `웹소켓 연결 종료`(session)
    }

    companion object {
        private const val MEMBER_ID = "test1234"
        private const val PASSWORD = "password"
    }
}
