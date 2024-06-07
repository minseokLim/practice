package com.minseoklim.toyproject2024.acceptance

import com.minseoklim.toyproject2024.acceptance.util.DatabaseCleanup
import com.minseoklim.toyproject2024.acceptance.util.RequestUtil
import io.restassured.RestAssured
import io.restassured.response.ExtractableResponse
import io.restassured.response.Response
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.http.HttpStatus
import org.springframework.test.context.ActiveProfiles

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class AcceptanceTest {

    @LocalServerPort
    private var port = 0

    @Autowired
    private lateinit var databaseCleanup: DatabaseCleanup

    @BeforeEach
    fun setUp() {
        RestAssured.port = port
        databaseCleanup.execute()
    }

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
        val logoutResponse = `로그아웃 요청`(newAccessToken, logoutRequest)

        // then
        로그아웃됨(logoutResponse)

        // when
        val validateTokenResponseAfterLogout = `토큰 유효성 검사 요청`(newAccessToken)

        // then
        `토큰 유효성 검사 실패`(validateTokenResponseAfterLogout)
    }

    private fun `회원 가입 요청`(request: Map<String, Any?>): ExtractableResponse<Response> {
        return RequestUtil.post("/members", null, request)
    }

    private fun `회원 가입됨`(response: ExtractableResponse<Response>) {
        assertThat(response.httpStatus()).isEqualTo(HttpStatus.CREATED)
        assertThat(response.extractId()).isNotNull
    }

    private fun `로그인 요청`(request: Map<String, Any>): ExtractableResponse<Response> {
        return RequestUtil.post("/login", null, request)
    }

    private fun 로그인됨(response: ExtractableResponse<Response>) {
        assertThat(response.httpStatus()).isEqualTo(HttpStatus.OK)
        assertThat(response.extractAccessToken()).isNotNull
        assertThat(response.extractRefreshToken()).isNotNull
    }

    private fun `토큰 유효성 검사 요청`(accessToken: String): ExtractableResponse<Response> {
        return RequestUtil.get("/validate-token", accessToken)
    }

    private fun `토큰 유효성 검사됨`(response: ExtractableResponse<Response>) {
        assertThat(response.httpStatus()).isEqualTo(HttpStatus.OK)
    }

    private fun `토큰 유효성 검사 실패`(response: ExtractableResponse<Response>) {
        assertThat(response.httpStatus()).isEqualTo(HttpStatus.UNAUTHORIZED)
    }

    private fun `토큰 재발급 요청`(request: Map<String, Any>): ExtractableResponse<Response> {
        return RequestUtil.post("/refresh-token", null, request)
    }

    private fun `토큰 재발급됨`(response: ExtractableResponse<Response>) {
        assertThat(response.httpStatus()).isEqualTo(HttpStatus.OK)
        assertThat(response.extractAccessToken()).isNotNull
        assertThat(response.extractRefreshToken()).isNotNull
    }

    private fun `로그아웃 요청`(accessToken: String, request: Map<String, Any>): ExtractableResponse<Response> {
        return RequestUtil.post("/logout", accessToken, request)
    }

    private fun 로그아웃됨(response: ExtractableResponse<Response>) {
        assertThat(response.httpStatus()).isEqualTo(HttpStatus.OK)
    }

    companion object {
        private const val MEMBER_ID = "test1234"
        private const val PASSWORD = "password"

        private fun ExtractableResponse<Response>.httpStatus(): HttpStatus {
            return HttpStatus.valueOf(this.statusCode())
        }

        private fun ExtractableResponse<Response>.extractId(): Int {
            return this.jsonPath().get("id")
        }

        private fun ExtractableResponse<Response>.extractAccessToken(): String {
            return this.jsonPath().get("accessToken")
        }

        private fun ExtractableResponse<Response>.extractRefreshToken(): String {
            return this.jsonPath().get("refreshToken")
        }
    }
}
