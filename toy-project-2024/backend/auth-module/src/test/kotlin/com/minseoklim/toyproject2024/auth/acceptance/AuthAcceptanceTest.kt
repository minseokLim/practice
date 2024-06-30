package com.minseoklim.toyproject2024.auth.acceptance

import com.minseoklim.toyproject2024.auth.acceptance.AuthAcceptanceTestFixture.`로그아웃 실패`
import com.minseoklim.toyproject2024.auth.acceptance.AuthAcceptanceTestFixture.`로그아웃 요청`
import com.minseoklim.toyproject2024.auth.acceptance.AuthAcceptanceTestFixture.로그아웃됨
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
        val logoutRequest = mapOf("accessToken" to "invalidAccessToken", "refreshToken" to "invalidRefreshToken")

        // when
        val logoutResponse = `로그아웃 요청`(logoutRequest)

        // then
        `로그아웃 실패`(logoutResponse)
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
