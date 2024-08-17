package com.minseoklim.toyproject2024.payment.infra

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.HttpHeaders
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestHeader
import java.time.LocalDateTime

@FeignClient(name = "port-one-client-v2", url = "\${port-one.base-url-v2}")
interface PortOneClientV2 {
    @GetMapping("/payments/{paymentUid}")
    fun getPayment(
        @RequestHeader(HttpHeaders.AUTHORIZATION) accessToken: String,
        @PathVariable paymentUid: String
    ): PaymentResponse

    data class PaymentResponse(
        val status: String,
        val id: String,
        val transactionId: String,
        val merchantId: String,
        val storeId: String,
        val channel: Channel,
        val version: String,
        val requestedAt: LocalDateTime,
        val updatedAt: LocalDateTime,
        val statusChangedAt: LocalDateTime,
        val orderName: String,
        val amount: Amount,
        val currency: String,
        val customer: Customer,
        val promotionId: String?,
        val isCulturalExpense: Boolean
    )

    data class Channel(
        val type: String,
        val id: String,
        val key: String,
        val name: String,
        val pgProvider: String,
        val pgMerchantId: String
    )

    data class Amount(
        val total: Long,
        val taxFree: Long,
        val vat: Long,
        val supply: Long,
        val discount: Long,
        val paid: Long,
        val cancelled: Long,
        val cancelledTaxFree: Long
    )

    data class Customer(
        val id: String
    )

    @PostMapping("/payments/{paymentUid}/cancel")
    fun cancelPayment(
        @RequestHeader(HttpHeaders.AUTHORIZATION) accessToken: String,
        @PathVariable paymentUid: String
    )
}
