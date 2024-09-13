package com.minseoklim.toyproject2024.test.util

import io.restassured.RestAssured
import io.restassured.http.ContentType
import io.restassured.response.ExtractableResponse
import io.restassured.response.Response

object RequestUtil {
    fun get(
        path: String,
        accessToken: String?,
        vararg pathParams: Any
    ): ExtractableResponse<Response> {
        return RestAssured
            .given().log().all()
            .apply {
                if (accessToken != null) {
                    this.auth().oauth2(accessToken)
                }
            }
            .`when`().get(path, *pathParams)
            .then().log().all().extract()
    }

    fun post(
        path: String,
        accessToken: String?,
        bodyParam: Map<String, Any?>? = null,
        vararg pathParams: Any
    ): ExtractableResponse<Response> {
        return RestAssured
            .given().log().all()
            .apply {
                if (accessToken != null) {
                    this.auth().oauth2(accessToken)
                }
            }
            .apply {
                if (bodyParam != null) {
                    this.body(bodyParam)
                }
            }
            .contentType(ContentType.JSON)
            .`when`().post(path, *pathParams)
            .then().log().all().extract()
    }

    fun put(
        path: String,
        accessToken: String?,
        bodyParam: Map<String, Any?>,
        vararg pathParams: Any
    ): ExtractableResponse<Response> {
        return RestAssured
            .given().log().all()
            .apply {
                if (accessToken != null) {
                    this.auth().oauth2(accessToken)
                }
            }
            .body(bodyParam)
            .contentType(ContentType.JSON)
            .`when`().put(path, *pathParams)
            .then().log().all().extract()
    }

    fun patch(
        path: String,
        accessToken: String?,
        bodyParam: Map<String, Any?>,
        vararg pathParams: Any
    ): ExtractableResponse<Response> {
        return RestAssured
            .given().log().all()
            .apply {
                if (accessToken != null) {
                    this.auth().oauth2(accessToken)
                }
            }
            .body(bodyParam)
            .contentType(ContentType.JSON)
            .`when`().patch(path, *pathParams)
            .then().log().all().extract()
    }

    fun delete(
        path: String,
        accessToken: String?,
        vararg pathParams: Any
    ): ExtractableResponse<Response> {
        return RestAssured
            .given().log().all()
            .apply {
                if (accessToken != null) {
                    this.auth().oauth2(accessToken)
                }
            }
            .`when`().delete(path, *pathParams)
            .then().log().all().extract()
    }
}
