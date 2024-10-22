package com.minseoklim.toyproject2024.notification.acceptance

import com.minseoklim.toyproject2024.auth.acceptance.AuthAcceptanceTestFixture.`로그인 요청`
import com.minseoklim.toyproject2024.auth.acceptance.AuthAcceptanceTestUtil.extractAccessToken
import com.minseoklim.toyproject2024.member.acceptance.MemberAcceptanceTestFixture.`회원 가입 요청`
import com.minseoklim.toyproject2024.notification.acceptance.NotificationAcceptanceTestFixture.`푸시 전송 요청`
import com.minseoklim.toyproject2024.notification.acceptance.NotificationAcceptanceTestFixture.`푸시 전송됨`
import com.minseoklim.toyproject2024.notification.acceptance.NotificationAcceptanceTestFixture.`푸시 토큰 등록 요청`
import com.minseoklim.toyproject2024.notification.acceptance.NotificationAcceptanceTestFixture.`푸시 토큰 등록됨`
import com.minseoklim.toyproject2024.test.AcceptanceTest
import org.junit.jupiter.api.Test

class NotificationAcceptanceTest : AcceptanceTest() {
    @Test
    fun `FCM 서비스`() {
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

        // given
        val registerPushTokenRequest = mapOf(
            "token" to "testToken"
        )

        // when
        val registerPushTokenResponse = `푸시 토큰 등록 요청`(accessToken, registerPushTokenRequest)

        // then
        `푸시 토큰 등록됨`(registerPushTokenResponse)

        // given
        val sendNotificationRequest = mapOf(
            "title" to "testTitle",
            "body" to "testBody",
            "imageUrl" to "testImageUrl"
        )

        // when
        val sendNotificationResponse = `푸시 전송 요청`(accessToken, sendNotificationRequest)

        // then
        `푸시 전송됨`(sendNotificationResponse)
    }

    companion object {
        private const val MEMBER_ID = "test1234"
        private const val PASSWORD = "password"
    }
}
