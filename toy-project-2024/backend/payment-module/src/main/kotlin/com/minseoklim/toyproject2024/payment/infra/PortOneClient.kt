package com.minseoklim.toyproject2024.payment.infra

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.HttpHeaders
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader

@FeignClient(name = "port-one-client", url = "\${port-one.base-url}")
interface PortOneClient {
    @PostMapping("/users/getToken")
    fun getAccessToken(request: Map<String, Any>): PortOneResponse

    @PostMapping("/subscribe/payments/onetime")
    fun requestCardPayment(
        @RequestHeader(HttpHeaders.AUTHORIZATION) accessToken: String,
        @RequestBody request: Map<String, Any>
    ): PortOneResponse

    @PostMapping("/payments/cancel")
    fun cancelPayment(
        @RequestHeader(HttpHeaders.AUTHORIZATION) accessToken: String,
        @RequestBody request: Map<String, Any>
    ): PortOneResponse

    data class PortOneResponse(
        val code: Int,
        val message: String?,
        val response: Map<String, Any>?
    ) {
        fun isError(): Boolean {
            return code != OK_CODE
        }

        companion object {
            private const val OK_CODE = 0
        }
    }
}
