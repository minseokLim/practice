package com.minseoklim.toyproject2024.payment.application

import com.minseoklim.toyproject2024.card.application.CardServiceHelper
import com.minseoklim.toyproject2024.card.domain.repository.CardRepository
import com.minseoklim.toyproject2024.common.util.TextEncryptUtil
import com.minseoklim.toyproject2024.payment.application.CardPaymentApi.CardPaymentRequest
import com.minseoklim.toyproject2024.payment.domain.repository.PaymentRepository
import com.minseoklim.toyproject2024.payment.dto.application.MakeCardPaymentInput
import com.minseoklim.toyproject2024.payment.dto.application.MakeCardPaymentOutput
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class MakeCardPaymentService(
    private val cardRepository: CardRepository,
    private val paymentRepository: PaymentRepository,
    private val cardPaymentApi: CardPaymentApi
) {
    fun make(memberId: Int, input: MakeCardPaymentInput): MakeCardPaymentOutput {
        val card = CardServiceHelper.getCard(cardRepository, input.cardId)
        card.checkAuthority(memberId)

        val cardPayment = paymentRepository.save(input.toEntity(memberId))

        cardPaymentApi.requestPayment(
            CardPaymentRequest(
                paymentUid = cardPayment.paymentUid.value,
                amount = input.amount,
                cardNumber = TextEncryptUtil.decrypt(card.cardNumber.encryptedValue),
                cardExpiry = TextEncryptUtil.decrypt(card.cardExpiry.encryptedValue),
                birth = TextEncryptUtil.decrypt(card.birth.encryptedValue),
                pwd2digit = TextEncryptUtil.decrypt(card.pwd2digit.encryptedValue),
                productName = input.productName
            )
        )

        return MakeCardPaymentOutput.of(cardPayment)
    }
}
