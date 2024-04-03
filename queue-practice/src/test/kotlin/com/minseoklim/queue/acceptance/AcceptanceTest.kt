package com.minseoklim.queue.acceptance

import com.minseoklim.queue.acceptance.util.RequestUtil
import com.minseoklim.queue.domain.QueueTokenUtil
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
import java.util.concurrent.CompletableFuture

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AcceptanceTest {

    @LocalServerPort
    private var port = 0

    @Autowired
    private lateinit var queueTokenUtil: QueueTokenUtil

    @BeforeEach
    fun setUp() {
        RestAssured.port = port
        queueTokenUtil.initialize()
    }

    @Test
    fun `부하 테스트`() {
        val tokenCreateResponses = (1..1000).map {
            CompletableFuture.supplyAsync {
                `토큰 생성 요청`()
            }
        }.map { it.join() }

        tokenCreateResponses.map {
            CompletableFuture.supplyAsync {
                `토큰 생성됨`(it)
            }
        }.map { it.join() }

        val tokens = tokenCreateResponses.map { it.response().jsonPath().get<String>("token") }

        val tokenCheckResponses = tokens.map {
            CompletableFuture.supplyAsync {
                `토큰 체크 요청`(it)
            }
        }.map { it.join() }

        tokenCheckResponses.map {
            CompletableFuture.supplyAsync {
                `토큰 체크됨`(it)
            }
        }.map { it.join() }

        val tokenDeleteResponses = tokens.map {
            CompletableFuture.supplyAsync {
                `토큰 삭제 요청`(it)
            }
        }.map { it.join() }

        tokenDeleteResponses.map {
            CompletableFuture.supplyAsync {
                `토큰 삭제됨`(it)
            }
        }.map { it.join() }
    }

    private fun `토큰 생성 요청`(): ExtractableResponse<Response> {
        return RequestUtil.post("/token", emptyMap())
    }

    private fun `토큰 생성됨`(response: ExtractableResponse<Response>) {
        assertThat(response.httpStatus()).isEqualTo(HttpStatus.OK)
        assertThat(response.jsonPath().get<String>("token")).isNotBlank()
    }

    private fun `토큰 체크 요청`(token: String): ExtractableResponse<Response> {
        return RequestUtil.get("/token?token={token}", token)
    }

    private fun `토큰 체크됨`(response: ExtractableResponse<Response>) {
        assertThat(response.httpStatus()).isEqualTo(HttpStatus.OK)
        assertThat(response.jsonPath().get<String>("token")).isNotBlank()
    }

    private fun `토큰 삭제 요청`(token: String): ExtractableResponse<Response> {
        return RequestUtil.delete("/token?token={token}", token)
    }

    private fun `토큰 삭제됨`(response: ExtractableResponse<Response>) {
        assertThat(response.httpStatus()).isEqualTo(HttpStatus.NO_CONTENT)
    }

    companion object {
        private fun ExtractableResponse<Response>.httpStatus(): HttpStatus {
            return HttpStatus.valueOf(this.statusCode())
        }
    }
}
