package com.minseoklim.queue.acceptance.util

import io.restassured.RestAssured
import io.restassured.http.ContentType
import io.restassured.response.ExtractableResponse
import io.restassured.response.Response

object RequestUtil {
    fun get(path: String, vararg pathParams: Any): ExtractableResponse<Response> {
        return RestAssured
            .given().log().all()
            .`when`().get(path, *pathParams)
            .then().log().all().extract()
    }

    fun post(path: String, bodyParam: Map<String, Any?>, vararg pathParams: Any): ExtractableResponse<Response> {
        return RestAssured
            .given().log().all()
            .body(bodyParam)
            .contentType(ContentType.JSON)
            .`when`().post(path, *pathParams)
            .then().log().all().extract()
    }

    fun put(path: String, bodyParam: Map<String, Any?>, vararg pathParams: Any): ExtractableResponse<Response> {
        return RestAssured
            .given().log().all()
            .body(bodyParam)
            .contentType(ContentType.JSON)
            .`when`().put(path, *pathParams)
            .then().log().all().extract()
    }

    fun patch(path: String, bodyParam: Map<String, Any?>, vararg pathParams: Any): ExtractableResponse<Response> {
        return RestAssured
            .given().log().all()
            .body(bodyParam)
            .contentType(ContentType.JSON)
            .`when`().patch(path, *pathParams)
            .then().log().all().extract()
    }

    fun delete(path: String, vararg pathParams: Any): ExtractableResponse<Response> {
        return RestAssured
            .given().log().all()
            .`when`().delete(path, *pathParams)
            .then().log().all().extract()
    }
}
