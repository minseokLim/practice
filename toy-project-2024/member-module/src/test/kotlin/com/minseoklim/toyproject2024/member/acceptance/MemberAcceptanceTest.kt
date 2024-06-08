package com.minseoklim.toyproject2024.member.acceptance

import com.minseoklim.toyproject2024.auth.acceptance.AuthAcceptanceTestFixture.`로그인 요청`
import com.minseoklim.toyproject2024.auth.acceptance.AuthAcceptanceTestUtil.NEVER_EXPIRED_ADMIN_ACCESS_TOKEN
import com.minseoklim.toyproject2024.auth.acceptance.AuthAcceptanceTestUtil.extractAccessToken
import com.minseoklim.toyproject2024.member.acceptance.MemberAcceptanceTestFixture.`내 계정 수정 요청`
import com.minseoklim.toyproject2024.member.acceptance.MemberAcceptanceTestFixture.`내 계정 수정됨`
import com.minseoklim.toyproject2024.member.acceptance.MemberAcceptanceTestFixture.`내 계정 조회 요청`
import com.minseoklim.toyproject2024.member.acceptance.MemberAcceptanceTestFixture.`내 계정 조회됨`
import com.minseoklim.toyproject2024.member.acceptance.MemberAcceptanceTestFixture.`내 계정 탈퇴 요청`
import com.minseoklim.toyproject2024.member.acceptance.MemberAcceptanceTestFixture.`내 계정 탈퇴됨`
import com.minseoklim.toyproject2024.member.acceptance.MemberAcceptanceTestFixture.`회원 가입 요청`
import com.minseoklim.toyproject2024.member.acceptance.MemberAcceptanceTestFixture.`회원 가입됨`
import com.minseoklim.toyproject2024.member.acceptance.MemberAcceptanceTestFixture.`회원 목록 조회 요청`
import com.minseoklim.toyproject2024.member.acceptance.MemberAcceptanceTestFixture.`회원 목록 조회됨`
import com.minseoklim.toyproject2024.member.acceptance.MemberAcceptanceTestFixture.`회원 조회 요청`
import com.minseoklim.toyproject2024.member.acceptance.MemberAcceptanceTestFixture.`회원 조회됨`
import com.minseoklim.toyproject2024.member.acceptance.MemberAcceptanceTestFixture.`회원 탈퇴 요청`
import com.minseoklim.toyproject2024.member.acceptance.MemberAcceptanceTestFixture.`회원 탈퇴됨`
import com.minseoklim.toyproject2024.test.AcceptanceTest
import com.minseoklim.toyproject2024.test.util.TestUtil.extractId
import org.junit.jupiter.api.Test

class MemberAcceptanceTest : AcceptanceTest() {

    @Test
    fun `회원 관리 서비스`() {
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

        // when
        val memberListResponse = `회원 목록 조회 요청`(NEVER_EXPIRED_ADMIN_ACCESS_TOKEN)

        // then
        `회원 목록 조회됨`(memberListResponse)

        // given
        val memberId = memberJoinResponse.extractId()

        // when
        val memberResponse = `회원 조회 요청`(NEVER_EXPIRED_ADMIN_ACCESS_TOKEN, memberId)

        // then
        `회원 조회됨`(memberResponse)

        // when
        val memberDeleteResponse = `회원 탈퇴 요청`(NEVER_EXPIRED_ADMIN_ACCESS_TOKEN, memberId)

        // then
        `회원 탈퇴됨`(memberDeleteResponse)
    }

    @Test
    fun `내 계정 관리 서비스`() {
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
        val loginResponse = `로그인 요청`(loginRequest)
        val accessToken = loginResponse.extractAccessToken()

        // when
        val myMemberResponse = `내 계정 조회 요청`(accessToken)

        // then
        `내 계정 조회됨`(myMemberResponse)

        // given
        val myMemberUpdateRequest = mapOf(
            "password" to "newPassword",
            "name" to "newName",
            "email" to "new@test.com"
        )

        // when
        val myMemberUpdateResponse = `내 계정 수정 요청`(accessToken, myMemberUpdateRequest)

        // then
        `내 계정 수정됨`(myMemberUpdateResponse)

        // when
        val myMemberDeleteResponse = `내 계정 탈퇴 요청`(accessToken)

        // then
        `내 계정 탈퇴됨`(myMemberDeleteResponse)
    }

    companion object {
        private const val MEMBER_ID = "test1234"
        private const val PASSWORD = "password"
    }
}
