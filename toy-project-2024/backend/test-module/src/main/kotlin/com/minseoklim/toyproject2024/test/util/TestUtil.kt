package com.minseoklim.toyproject2024.test.util

import io.restassured.response.ExtractableResponse
import io.restassured.response.Response
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.runBlocking
import org.springframework.http.HttpStatus
import java.util.concurrent.Executors

object TestUtil {
    fun ExtractableResponse<Response>.httpStatus(): HttpStatus {
        return HttpStatus.valueOf(this.statusCode())
    }

    fun ExtractableResponse<Response>.extractId(): Long {
        return this.jsonPath()["id"]
    }

    fun ExtractableResponse<Response>.extractIds(): List<Long> {
        return this.jsonPath().getList("content.id")
    }

    fun ExtractableResponse<Response>.extractVersion(): Long {
        return this.jsonPath()["version"]
    }

    fun <T> runSynchronously(
        times: Int,
        vararg actions: (Int) -> T
    ): List<T> {
        return (0 until times).flatMap {
            actions.map { action ->
                action(it)
            }
        }
    }

    fun <T> runConcurrently(
        times: Int,
        vararg actions: (Int) -> T
    ): List<T> {
        return runBlocking(Executors.newFixedThreadPool(times).asCoroutineDispatcher()) {
            (0 until times).flatMap {
                actions.map { action ->
                    async {
                        action(it)
                    }
                }
            }.awaitAll()
        }
    }
}
