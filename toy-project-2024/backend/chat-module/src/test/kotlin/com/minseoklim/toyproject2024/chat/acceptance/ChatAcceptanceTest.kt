package com.minseoklim.toyproject2024.chat.acceptance

import com.minseoklim.toyproject2024.auth.acceptance.AuthAcceptanceTestFixture.`로그인 요청`
import com.minseoklim.toyproject2024.auth.acceptance.AuthAcceptanceTestUtil.extractAccessToken
import com.minseoklim.toyproject2024.chat.acceptance.ChatAcceptanceTestFixture.`마지막 읽은 메세지 업데이트 요청`
import com.minseoklim.toyproject2024.chat.acceptance.ChatAcceptanceTestFixture.`마지막 읽은 메세지 업데이트됨`
import com.minseoklim.toyproject2024.chat.acceptance.ChatAcceptanceTestFixture.`채팅 메세지 목록 조회 요청`
import com.minseoklim.toyproject2024.chat.acceptance.ChatAcceptanceTestFixture.`채팅 메세지 목록 조회됨`
import com.minseoklim.toyproject2024.chat.acceptance.ChatAcceptanceTestFixture.`채팅 메세지 전송 요청`
import com.minseoklim.toyproject2024.chat.acceptance.ChatAcceptanceTestFixture.`채팅 방 목록 조회 요청`
import com.minseoklim.toyproject2024.chat.acceptance.ChatAcceptanceTestFixture.`채팅 방 목록 조회됨`
import com.minseoklim.toyproject2024.chat.acceptance.ChatAcceptanceTestFixture.`채팅 방 생성 요청`
import com.minseoklim.toyproject2024.chat.acceptance.ChatAcceptanceTestFixture.`채팅 방 생성됨`
import com.minseoklim.toyproject2024.member.acceptance.MemberAcceptanceTestFixture.`회원 가입 요청`
import com.minseoklim.toyproject2024.test.AcceptanceTest
import com.minseoklim.toyproject2024.test.util.TestUtil.extractId
import com.minseoklim.toyproject2024.websocket.acceptance.WebSocketAcceptanceTestFixture.`웹소켓 연결 요청`
import com.minseoklim.toyproject2024.websocket.acceptance.WebSocketAcceptanceTestFixture.`웹소켓 토큰 발급 요청`
import org.junit.jupiter.api.Test

class ChatAcceptanceTest : AcceptanceTest() {
    @Test
    fun `채팅 서비스`() {
        // given
        val memberJoinRequest1 = mapOf(
            "loginId" to MEMBER_ID_1,
            "password" to PASSWORD_1,
            "name" to "testName",
            "email" to "test1@test.com"
        )
        val memberJoinResponse1 = `회원 가입 요청`(memberJoinRequest1)
        val memberId1 = memberJoinResponse1.extractId()
        val memberJoinRequest2 = mapOf(
            "loginId" to MEMBER_ID_2,
            "password" to PASSWORD_2,
            "name" to "testName",
            "email" to "test2@test.com"
        )
        val memberJoinResponse2 = `회원 가입 요청`(memberJoinRequest2)
        val memberId2 = memberJoinResponse2.extractId()

        // given
        val loginRequest1 = mapOf(
            "loginId" to MEMBER_ID_1,
            "password" to PASSWORD_1
        )
        val loginResponse1 = `로그인 요청`(loginRequest1)
        val accessToken1 = loginResponse1.extractAccessToken()
        val loginRequest2 = mapOf(
            "loginId" to MEMBER_ID_2,
            "password" to PASSWORD_2
        )
        val loginResponse2 = `로그인 요청`(loginRequest2)
        val accessToken2 = loginResponse2.extractAccessToken()

        // given
        val tokenResponse1 = `웹소켓 토큰 발급 요청`(accessToken1)
        val token1: String = tokenResponse1.jsonPath().get("token")
        val session1 = `웹소켓 연결 요청`(this.port, token1)
        val tokenResponse2 = `웹소켓 토큰 발급 요청`(accessToken2)
        val token2: String = tokenResponse2.jsonPath().get("token")
        val session2 = `웹소켓 연결 요청`(this.port, token2)

        // given
        val makeChatRoomRequest = mapOf(
            "memberIds" to listOf(memberId1, memberId2)
        )

        // when
        val makeChatRoomResponse = `채팅 방 생성 요청`(accessToken1, makeChatRoomRequest)

        // then
        `채팅 방 생성됨`(makeChatRoomResponse)

        // when
        val queryChatRoomListResponse = `채팅 방 목록 조회 요청`(accessToken1)

        // then
        `채팅 방 목록 조회됨`(queryChatRoomListResponse)

        // given
        val chatRoomId = makeChatRoomResponse.extractId().toLong()
        val sendMessageRequest = mapOf("content" to "test message")
        `채팅 메세지 전송 요청`(session1, chatRoomId, sendMessageRequest)

        Thread.sleep(100) // wait for message to be sent

        // when
        val queryMessageListResponse = `채팅 메세지 목록 조회 요청`(accessToken1, chatRoomId, 10)

        // then
        `채팅 메세지 목록 조회됨`(queryMessageListResponse)

        // given
        val messageId = queryMessageListResponse.jsonPath().getList<Int>("id")[0]
        val updateLastReadMessageRequest = mapOf("lastReadMessageId" to messageId)

        // when
        val updateLastReadMessageResponse = `마지막 읽은 메세지 업데이트 요청`(accessToken1, chatRoomId, updateLastReadMessageRequest)

        // then
        `마지막 읽은 메세지 업데이트됨`(updateLastReadMessageResponse)
    }

    companion object {
        private const val MEMBER_ID_1 = "test1234"
        private const val PASSWORD_1 = "password"

        private const val MEMBER_ID_2 = "test5678"
        private const val PASSWORD_2 = "password"
    }
}
