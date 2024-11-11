package com.minseoklim.toyproject2024.feed.acceptance

import com.minseoklim.toyproject2024.auth.acceptance.AuthAcceptanceTestFixture.`로그인 요청`
import com.minseoklim.toyproject2024.auth.acceptance.AuthAcceptanceTestUtil.extractAccessToken
import com.minseoklim.toyproject2024.feed.acceptance.FeedAcceptanceTestFixture.`피드 등록 요청`
import com.minseoklim.toyproject2024.feed.acceptance.FeedAcceptanceTestFixture.`피드 등록됨`
import com.minseoklim.toyproject2024.feed.acceptance.FeedAcceptanceTestFixture.`피드 목록 조회 요청`
import com.minseoklim.toyproject2024.feed.acceptance.FeedAcceptanceTestFixture.`피드 목록 조회됨`
import com.minseoklim.toyproject2024.member.acceptance.MemberAcceptanceTestFixture.`회원 가입 요청`
import com.minseoklim.toyproject2024.test.AcceptanceTest
import org.junit.jupiter.api.Test
import org.springframework.core.io.ClassPathResource

class FeedAcceptanceTest : AcceptanceTest() {
    @Test
    fun `피드 서비스`() {
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
        val sampleAttachedFiles = listOf(ClassPathResource("/profile.jpg").file)
        val registerFeedRequest = mapOf(
            "title" to "testTitle",
            "content" to "testContent",
            "feedType" to "IMAGE"
        )

        // when
        val registerFeedResponse = `피드 등록 요청`(accessToken, sampleAttachedFiles, registerFeedRequest)

        // then
        `피드 등록됨`(registerFeedResponse)

        // when
        val queryFeedResponse = `피드 목록 조회 요청`(accessToken, 10)

        // then
        `피드 목록 조회됨`(queryFeedResponse)
    }

    companion object {
        private const val MEMBER_ID = "test1234"
        private const val PASSWORD = "password"
    }
}
