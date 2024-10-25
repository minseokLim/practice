package com.minseoklim.toyproject2024.location.acceptance

import com.minseoklim.toyproject2024.auth.acceptance.AuthAcceptanceTestFixture.`로그인 요청`
import com.minseoklim.toyproject2024.auth.acceptance.AuthAcceptanceTestUtil.extractAccessToken
import com.minseoklim.toyproject2024.location.acceptance.LocationAcceptanceTestFixture.`내 주변 위치 목록 조회 요청`
import com.minseoklim.toyproject2024.location.acceptance.LocationAcceptanceTestFixture.`내 주변 위치 목록 조회됨`
import com.minseoklim.toyproject2024.location.acceptance.LocationAcceptanceTestFixture.`위치 등록 요청`
import com.minseoklim.toyproject2024.location.acceptance.LocationAcceptanceTestFixture.`위치 등록됨`
import com.minseoklim.toyproject2024.member.acceptance.MemberAcceptanceTestFixture.`회원 가입 요청`
import com.minseoklim.toyproject2024.test.AcceptanceTest
import org.junit.jupiter.api.Test

class LocationAcceptanceTest : AcceptanceTest() {
    @Test
    fun `위치 서비스`() {
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
        val registerLocationRequest = mapOf(
            "latitude" to 37.123456,
            "longitude" to 127.123456
        )

        // when
        val registerLocationResponse = `위치 등록 요청`(accessToken, registerLocationRequest)

        // then
        `위치 등록됨`(registerLocationResponse)

        // when
        val queryNearbyLocationsResponse = `내 주변 위치 목록 조회 요청`(accessToken, 1000)

        // then
        `내 주변 위치 목록 조회됨`(queryNearbyLocationsResponse)
    }

    companion object {
        private const val MEMBER_ID = "test1234"
        private const val PASSWORD = "password"
    }
}
