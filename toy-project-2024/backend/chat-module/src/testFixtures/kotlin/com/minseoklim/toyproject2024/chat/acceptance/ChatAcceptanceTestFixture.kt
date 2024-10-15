package com.minseoklim.toyproject2024.chat.acceptance

import com.minseoklim.toyproject2024.test.util.RequestUtil
import com.minseoklim.toyproject2024.test.util.TestUtil.httpStatus
import io.restassured.response.ExtractableResponse
import io.restassured.response.Response
import org.assertj.core.api.Assertions.assertThat
import org.springframework.http.HttpStatus
import org.springframework.messaging.simp.stomp.StompSession

@Suppress("ktlint:standard:function-naming")
object ChatAcceptanceTestFixture {
    fun `채팅 방 생성 요청`(
        accessToken: String,
        request: Map<String, Any?>
    ): ExtractableResponse<Response> {
        return RequestUtil.post("/chat-rooms", accessToken, request)
    }

    fun `채팅 방 생성됨`(response: ExtractableResponse<Response>) {
        assertThat(response.httpStatus()).isEqualTo(HttpStatus.CREATED)
    }

    fun `채팅 방 목록 조회 요청`(accessToken: String): ExtractableResponse<Response> {
        return RequestUtil.get("/chat-rooms", accessToken)
    }

    fun `채팅 방 목록 조회됨`(response: ExtractableResponse<Response>) {
        assertThat(response.httpStatus()).isEqualTo(HttpStatus.OK)
        assertThat(response.jsonPath().getList<Any>(".")).isNotEmpty
    }

    fun `채팅 메세지 전송 요청`(
        session: StompSession,
        chatRoomId: Long,
        request: Map<String, Any?>
    ) {
        session.send("/app/chat-rooms/$chatRoomId/messages", request)
    }

    fun `채팅 메세지 목록 조회 요청`(
        accessToken: String,
        chatRoomId: Long,
        size: Int
    ): ExtractableResponse<Response> {
        return RequestUtil.get(
            "/chat-rooms/$chatRoomId/messages?size=$size",
            accessToken
        )
    }

    fun `채팅 메세지 목록 조회됨`(response: ExtractableResponse<Response>) {
        assertThat(response.httpStatus()).isEqualTo(HttpStatus.OK)
        assertThat(response.jsonPath().getList<Any>(".")).isNotEmpty
    }

    fun `마지막 읽은 메세지 업데이트 요청`(
        accessToken: String,
        chatRoomId: Long,
        request: Map<String, Any?>
    ): ExtractableResponse<Response> {
        return RequestUtil.post(
            "/chat-rooms/$chatRoomId/last-read-message",
            accessToken,
            request
        )
    }

    fun `마지막 읽은 메세지 업데이트됨`(response: ExtractableResponse<Response>) {
        assertThat(response.httpStatus()).isEqualTo(HttpStatus.OK)
    }
}
