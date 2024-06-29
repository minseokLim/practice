package com.minseoklim.toyproject2024.test.util

import io.restassured.response.ExtractableResponse
import io.restassured.response.Response
import org.assertj.core.api.Assertions.assertThat
import org.springframework.http.HttpStatus

object TestUtil {
    fun ExtractableResponse<Response>.httpStatus(): HttpStatus {
        return HttpStatus.valueOf(this.statusCode())
    }

    fun ExtractableResponse<Response>.extractId(): Int {
        return this.jsonPath()["id"]
    }

    /**
     * obj1, obj2는 동등하고 obj3는 다른 객체인 경우를 테스트합니다.
     */
    fun <T> testEqualsAndHashCode(obj1: T, obj2: T, obj3: T) {
        assertThat(obj1).isEqualTo(obj2)
        assertThat(obj1).isNotEqualTo(obj3)
        assertThat(obj1).isEqualTo(obj1)
        assertThat(obj1).isNotEqualTo(null)
        assertThat(obj1).isNotEqualTo(Any())
        assertThat(hashSetOf(obj1, obj2, obj3)).hasSize(2)
    }
}
