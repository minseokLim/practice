package com.minseoklim.toyproject2024.auth.acceptance

import com.minseoklim.toyproject2024.auth.acceptance.AuthAcceptanceTestFixture.`로그아웃 실패`
import com.minseoklim.toyproject2024.auth.acceptance.AuthAcceptanceTestFixture.`로그아웃 요청`
import com.minseoklim.toyproject2024.auth.acceptance.AuthAcceptanceTestFixture.로그아웃됨
import com.minseoklim.toyproject2024.auth.acceptance.AuthAcceptanceTestFixture.`로그인 실패`
import com.minseoklim.toyproject2024.auth.acceptance.AuthAcceptanceTestFixture.`로그인 요청`
import com.minseoklim.toyproject2024.auth.acceptance.AuthAcceptanceTestFixture.`로그인 화면으로 이동`
import com.minseoklim.toyproject2024.auth.acceptance.AuthAcceptanceTestFixture.`로그인 화면으로 이동됨`
import com.minseoklim.toyproject2024.auth.acceptance.AuthAcceptanceTestFixture.로그인됨
import com.minseoklim.toyproject2024.auth.acceptance.AuthAcceptanceTestFixture.`모든 토큰 유효성 검사 실패`
import com.minseoklim.toyproject2024.auth.acceptance.AuthAcceptanceTestFixture.`잘못된 접근 화면으로 이동됨`
import com.minseoklim.toyproject2024.auth.acceptance.AuthAcceptanceTestFixture.`토큰 유효성 검사 실패`
import com.minseoklim.toyproject2024.auth.acceptance.AuthAcceptanceTestFixture.`토큰 유효성 검사 요청`
import com.minseoklim.toyproject2024.auth.acceptance.AuthAcceptanceTestFixture.`토큰 유효성 검사됨`
import com.minseoklim.toyproject2024.auth.acceptance.AuthAcceptanceTestFixture.`토큰 재발급 요청`
import com.minseoklim.toyproject2024.auth.acceptance.AuthAcceptanceTestFixture.`토큰 재발급됨`
import com.minseoklim.toyproject2024.auth.acceptance.AuthAcceptanceTestFixture.`회원 가입 화면으로 이동`
import com.minseoklim.toyproject2024.auth.acceptance.AuthAcceptanceTestFixture.`회원 가입 화면으로 이동됨`
import com.minseoklim.toyproject2024.auth.acceptance.AuthAcceptanceTestFixture.`회원 전체 로그아웃 요청`
import com.minseoklim.toyproject2024.auth.acceptance.AuthAcceptanceTestFixture.`회원 전체 로그아웃됨`
import com.minseoklim.toyproject2024.auth.acceptance.AuthAcceptanceTestUtil.extractAccessToken
import com.minseoklim.toyproject2024.auth.acceptance.AuthAcceptanceTestUtil.extractRefreshToken
import com.minseoklim.toyproject2024.member.acceptance.MemberAcceptanceTestFixture.`회원 가입 요청`
import com.minseoklim.toyproject2024.member.acceptance.MemberAcceptanceTestFixture.`회원 가입됨`
import com.minseoklim.toyproject2024.test.AcceptanceTest
import com.minseoklim.toyproject2024.test.util.TestUtil
import org.junit.jupiter.api.Test

class AuthAcceptanceTest : AcceptanceTest() {

    @Test
    fun `회원 인증 서비스`() {
        // given
        val memberRequest = mapOf(
            "loginId" to MEMBER_ID,
            "password" to PASSWORD,
            "name" to "testName",
            "email" to "test@test.com"
        )

        // when
        val memberJoinResponse = `회원 가입 요청`(memberRequest)

        // then
        `회원 가입됨`(memberJoinResponse)

        // given
        val loginRequest = mapOf(
            "loginId" to MEMBER_ID,
            "password" to PASSWORD
        )

        // when
        val loginResponse = `로그인 요청`(loginRequest)

        // then
        로그인됨(loginResponse)

        // given
        val accessToken = loginResponse.extractAccessToken()

        // when
        val validateTokenResponse = `토큰 유효성 검사 요청`(accessToken)

        // then
        `토큰 유효성 검사됨`(validateTokenResponse)

        // given
        val refreshToken = loginResponse.extractRefreshToken()
        val refreshTokenRequest = mapOf(
            "accessToken" to accessToken,
            "refreshToken" to refreshToken
        )

        // when
        val refreshTokenResponse = `토큰 재발급 요청`(refreshTokenRequest)

        // then
        `토큰 재발급됨`(refreshTokenResponse)

        // given
        val newAccessToken = refreshTokenResponse.extractAccessToken()
        val newRefreshToken = refreshTokenResponse.extractRefreshToken()
        val logoutRequest = mapOf(
            "accessToken" to newAccessToken,
            "refreshToken" to newRefreshToken
        )

        // when
        val logoutResponse = `로그아웃 요청`(logoutRequest)

        // then
        로그아웃됨(logoutResponse)

        // when
        val validateTokenResponseAfterLogout = `토큰 유효성 검사 요청`(newAccessToken)

        // then
        `토큰 유효성 검사 실패`(validateTokenResponseAfterLogout)

        // given
        val loginResponses = TestUtil.runSynchronously(10, { `로그인 요청`(loginRequest) })

        // when
        val logoutAllResponse = `회원 전체 로그아웃 요청`(loginResponses.first().extractAccessToken())

        // then
        `회원 전체 로그아웃됨`(logoutAllResponse)

        // when
        val validateTokenResponses = TestUtil.runSynchronously(loginResponses.size, {
            `토큰 유효성 검사 요청`(loginResponses[it].extractAccessToken())
        })

        // then
        `모든 토큰 유효성 검사 실패`(validateTokenResponses)
    }

    @Test
    fun `예외 상황`() {
        // given
        val memberRequest = mapOf(
            "loginId" to MEMBER_ID,
            "password" to PASSWORD,
            "name" to "testName",
            "email" to "test@test.com"
        )
        `회원 가입 요청`(memberRequest)

        // given
        val loginRequest1 = mapOf(
            "loginId" to MEMBER_ID,
            "password" to PASSWORD
        )
        val oldLoginResponse = `로그인 요청`(loginRequest1)
        val oldAccessToken = oldLoginResponse.extractAccessToken()
        val oldRefreshToken = oldLoginResponse.extractRefreshToken()

        // given
        val logoutRequest1 = mapOf(
            "accessToken" to oldAccessToken,
            "refreshToken" to oldRefreshToken
        )
        `로그아웃 요청`(logoutRequest1)

        // when
        val logoutResponse1 = `로그아웃 요청`(logoutRequest1)

        // then
        `로그아웃 실패`(logoutResponse1)

        // given
        val newLoginResponse = `로그인 요청`(loginRequest1)
        val newAccessToken = newLoginResponse.extractAccessToken()

        // given
        val logoutRequest2 = mapOf(
            "accessToken" to newAccessToken,
            "refreshToken" to oldRefreshToken
        )

        // when
        val logoutResponse2 = `로그아웃 요청`(logoutRequest2)

        // then
        `로그아웃 실패`(logoutResponse2)

        // given
        val logoutRequest3 = mapOf(
            "accessToken" to "invalidAccessToken",
            "refreshToken" to "invalidRefreshToken"
        )

        // when
        val logoutResponse3 = `로그아웃 요청`(logoutRequest3)

        // then
        `로그아웃 실패`(logoutResponse3)

        // when
        val validateTokenResponse1 = `토큰 유효성 검사 요청`(oldRefreshToken)

        // then
        `토큰 유효성 검사 실패`(validateTokenResponse1)

        // when
        val validateTokenResponse2 = `토큰 유효성 검사 요청`("")

        // then
        `토큰 유효성 검사 실패`(validateTokenResponse2)

        // given
        val loginRequest2 = mapOf(
            "loginId" to "invalidLoginId",
            "password" to PASSWORD
        )

        // when
        val loginResponse2 = `로그인 요청`(loginRequest2)

        // then
        `로그인 실패`(loginResponse2)
    }

    @Test
    fun `화면 테스트`() {
        // given
        val queryParameter = "?redirectUrl=http://localhost:8080"

        // when
        val loginPageResponse1 = `로그인 화면으로 이동`(queryParameter)

        // then
        `로그인 화면으로 이동됨`(loginPageResponse1)

        // when
        val loginPageResponse2 = `로그인 화면으로 이동`("")

        // then
        `잘못된 접근 화면으로 이동됨`(loginPageResponse2)

        // when
        val joinPageResponse1 = `회원 가입 화면으로 이동`(queryParameter)

        // then
        `회원 가입 화면으로 이동됨`(joinPageResponse1)

        // when
        val joinPageResponse2 = `회원 가입 화면으로 이동`("")

        // then
        `잘못된 접근 화면으로 이동됨`(joinPageResponse2)
    }

    companion object {
        private const val MEMBER_ID = "test1234"
        private const val PASSWORD = "password"
    }
}
