package com.minseoklim.toyproject2024.payment.infra

import com.minseoklim.toyproject2024.payment.application.CardPaymentApi
import com.minseoklim.toyproject2024.payment.application.CardPaymentApi.CardPaymentCancelRequest
import com.minseoklim.toyproject2024.payment.application.CardPaymentApi.CardPaymentRequest
import com.minseoklim.toyproject2024.payment.config.PortOneProperty
import com.minseoklim.toyproject2024.payment.infra.PortOneClient.PortOneResponse
import mu.KLogging
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.stereotype.Component
import java.time.Duration

@Component
class PortOneApi(
    private val portOneProperty: PortOneProperty,
    private val portOneClient: PortOneClient,
    private val redisTemplate: StringRedisTemplate
) : CardPaymentApi {
    override fun requestPayment(request: CardPaymentRequest) {
        val accessToken = getAccessToken()
        val response = requestCardPayment(accessToken, request)

        val status = extractStatus(response)

        if ("paid" != status || response.isError()) {
            logger.info("결제 실패 : ${response.message}")
            throw IllegalStateException("결제에 실패하였습니다")
        }
    }

    private fun requestCardPayment(accessToken: String, request: CardPaymentRequest): PortOneResponse {
        val param = mapOf(
            "merchant_uid" to request.paymentUid,
            "amount" to request.amount,
            "card_number" to request.cardNumber,
            "expiry" to request.cardExpiry,
            "birth" to request.birth,
            "pwd_2digit" to request.pwd2digit,
            "pg" to portOneProperty.pg,
            "name" to request.productName
        )
        return portOneClient.requestCardPayment(accessToken, param)
    }

    private fun extractStatus(response: PortOneResponse): String {
        return response.response?.getValue("status")?.toString() ?: throw IllegalStateException("결제에 실패하였습니다")
    }

    override fun cancelPayment(request: CardPaymentCancelRequest) {
        val accessToken = getAccessToken()
        val response = cancelCardPayment(accessToken, request)

        if (response.isError()) {
            logger.info("결제 취소 실패 : ${response.message}")
            throw IllegalStateException("결제 취소에 실패하였습니다 [${response.message}]")
        }
    }

    private fun cancelCardPayment(accessToken: String, request: CardPaymentCancelRequest): PortOneResponse {
        val param = mapOf(
            "merchant_uid" to request.paymentUid,
            "amount" to request.amount
        )
        return portOneClient.cancelPayment(accessToken, param)
    }

    private fun getAccessToken(): String {
        if (redisTemplate.opsForValue()[ACCESS_TOKEN_KEY] != null) {
            return checkNotNull(redisTemplate.opsForValue()[ACCESS_TOKEN_KEY])
        }

        val response = requestAccessToken()

        if (response.isError()) {
            logger.info("액세스 토큰 발급 실패 : ${response.message}")
            throw IllegalStateException("액세스 토큰을 발급에 실패하였습니다")
        }

        val accessToken = extractAccessToken(response)
        redisTemplate.opsForValue().set(ACCESS_TOKEN_KEY, accessToken, Duration.ofMinutes(ACCESS_TOKEN_EXPIRE_MINUTES))

        return accessToken
    }

    private fun requestAccessToken(): PortOneResponse {
        val request = mapOf(
            "imp_key" to portOneProperty.key,
            "imp_secret" to portOneProperty.secret
        )
        return portOneClient.getAccessToken(request)
    }

    private fun extractAccessToken(response: PortOneResponse): String {
        return (response.response?.getValue("access_token")?.toString()
            ?: throw IllegalStateException("액세스 토큰을 발급에 실패하였습니다"))
    }

    companion object : KLogging() {
        private const val ACCESS_TOKEN_KEY = "port-one-access-token"
        private const val ACCESS_TOKEN_EXPIRE_MINUTES = 28L // 토큰의 만료시간은 30분, 이보다 적은 28분으로 설정
    }
}
