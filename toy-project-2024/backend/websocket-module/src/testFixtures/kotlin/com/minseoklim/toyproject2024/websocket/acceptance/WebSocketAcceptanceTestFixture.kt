package com.minseoklim.toyproject2024.websocket.acceptance

import com.minseoklim.toyproject2024.test.util.RequestUtil
import com.minseoklim.toyproject2024.test.util.TestUtil.httpStatus
import io.restassured.response.ExtractableResponse
import io.restassured.response.Response
import org.assertj.core.api.Assertions.assertThat
import org.springframework.http.HttpStatus
import org.springframework.messaging.simp.stomp.StompSession
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter
import org.springframework.web.socket.client.standard.StandardWebSocketClient
import org.springframework.web.socket.messaging.WebSocketStompClient

@Suppress("ktlint:standard:function-naming")
object WebSocketAcceptanceTestFixture {
    fun `웹소켓 토큰 발급 요청`(accessToken: String): ExtractableResponse<Response> {
        return RequestUtil.post("/websocket/token", accessToken)
    }

    fun `웹소켓 토큰 발급됨`(response: ExtractableResponse<Response>) {
        assertThat(response.httpStatus()).isEqualTo(HttpStatus.OK)
    }

    fun `웹소켓 연결 요청`(
        port: Int,
        token: String
    ): StompSession {
        val client = StandardWebSocketClient()
        val stompClient = WebSocketStompClient(client)
        val handler = object : StompSessionHandlerAdapter() {}

        return stompClient.connectAsync("ws://localhost:$port/ws?token=$token", handler).join()
    }

    fun `웹소켓 연결됨`(session: StompSession) {
        assertThat(session.isConnected).isTrue
    }

    fun `웹소켓 연결 종료`(session: StompSession) {
        session.disconnect()
    }
}
