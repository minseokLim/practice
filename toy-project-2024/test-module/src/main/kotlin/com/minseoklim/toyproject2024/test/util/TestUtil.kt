package com.minseoklim.toyproject2024.test.util

import io.restassured.response.ExtractableResponse
import io.restassured.response.Response
import org.springframework.http.HttpStatus

object TestUtil {
    fun ExtractableResponse<Response>.httpStatus(): HttpStatus {
        return HttpStatus.valueOf(this.statusCode())
    }

    fun ExtractableResponse<Response>.extractId(): Int {
        return this.jsonPath()["id"]
    }
}
