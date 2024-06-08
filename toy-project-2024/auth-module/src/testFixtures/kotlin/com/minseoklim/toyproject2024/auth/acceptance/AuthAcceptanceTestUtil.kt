package com.minseoklim.toyproject2024.auth.acceptance

import io.restassured.response.ExtractableResponse
import io.restassured.response.Response

object AuthAcceptanceTestUtil {
    const val NEVER_EXPIRED_ADMIN_ACCESS_TOKEN =
        "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJjOTlmMWFlZS01MmZiLTQ2YmUtYmJlNS1jODJmYWYwOTFiZGYiLCJzdWIiOiIyMTQ3NDgzNjQ3IiwiYXV0aCI6IlJPTEVfQURNSU4sUk9MRV9NRU1CRVIiLCJ0eXAiOiJBQ0NFU1MiLCJpYXQiOjE3MTc4ODI0MDksImV4cCI6OTIyMzM3MjAzNjg1NDc3NX0.m4lB_KQyJVhrj2MXTRTtBqtGm9XcdOac1arLOxr64Qo"

    fun ExtractableResponse<Response>.extractAccessToken(): String {
        return this.jsonPath().get("accessToken")
    }

    fun ExtractableResponse<Response>.extractRefreshToken(): String {
        return this.jsonPath().get("refreshToken")
    }
}
