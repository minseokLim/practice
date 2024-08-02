package com.minseoklim.toyproject2024.payment.application

import com.minseoklim.toyproject2024.card.application.CardServiceHelper
import com.minseoklim.toyproject2024.card.domain.repository.CardRepository
import com.minseoklim.toyproject2024.common.util.TextEncryptUtil
import com.minseoklim.toyproject2024.payment.application.CardPaymentApi.CardPaymentRequest
import com.minseoklim.toyproject2024.payment.domain.repository.PaymentRepository
import com.minseoklim.toyproject2024.payment.dto.MakeCardPaymentRequest
import com.minseoklim.toyproject2024.payment.dto.MakeCardPaymentResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class MakeCardPaymentService(
    private val cardRepository: CardRepository,
    private val paymentRepository: PaymentRepository,
    private val cardPaymentApi: CardPaymentApi
) {
    fun make(memberId: Int, request: MakeCardPaymentRequest): MakeCardPaymentResponse {
        val card = CardServiceHelper.getCard(cardRepository, request.cardId)
        card.checkAuthority(memberId)

        val cardPayment = paymentRepository.save(request.toEntity(memberId))

        cardPaymentApi.requestPayment(
            CardPaymentRequest(
                paymentUid = cardPayment.paymentUid.value,
                amount = request.amount,
                cardNumber = TextEncryptUtil.decrypt(card.cardNumber.encryptedValue),
                cardExpiry = TextEncryptUtil.decrypt(card.cardExpiry.encryptedValue),
                birth = TextEncryptUtil.decrypt(card.birth.encryptedValue),
                pwd2digit = TextEncryptUtil.decrypt(card.pwd2digit.encryptedValue),
                productName = request.productName
            )
        )

        return MakeCardPaymentResponse.of(cardPayment)
    }
}
