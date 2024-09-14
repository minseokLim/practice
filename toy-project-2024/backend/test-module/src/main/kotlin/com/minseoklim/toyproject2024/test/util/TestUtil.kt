package com.minseoklim.toyproject2024.test.util

import io.restassured.response.ExtractableResponse
import io.restassured.response.Response
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.springframework.http.HttpStatus
import java.util.concurrent.Executors

object TestUtil {
    fun ExtractableResponse<Response>.httpStatus(): HttpStatus {
        return HttpStatus.valueOf(this.statusCode())
    }

    fun ExtractableResponse<Response>.extractId(): Int {
        return this.jsonPath()["id"]
    }

    fun ExtractableResponse<Response>.extractIds(): List<Int> {
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

    /**
     * obj1, obj2는 동등하고 obj3는 다른 객체인 경우를 테스트합니다.
     */
    inline fun <reified T : Any> testEqualsAndHashCode(
        obj1: T,
        obj2: T,
        obj3: T
    ) {
        assertThat(obj1).isEqualTo(obj2)
        assertThat(obj1).isNotEqualTo(obj3)
        assertThat(obj1).isEqualTo(obj1)
        assertThat(obj1).isNotEqualTo(null)
        assertThat(obj1).isNotEqualTo(Any())

        val proxy = HibernateProxyUtil.createHibernateProxy(obj1)
        assertThat(proxy).isEqualTo(obj1)
        assertThat(obj1).isEqualTo(proxy)

        assertThat(obj1).hasSameHashCodeAs(obj2)
        assertThat(proxy).hasSameHashCodeAs(obj2)
    }
}
