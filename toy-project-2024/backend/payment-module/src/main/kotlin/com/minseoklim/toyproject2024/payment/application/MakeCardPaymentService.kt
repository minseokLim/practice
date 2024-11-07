package com.minseoklim.toyproject2024.payment.application

import com.minseoklim.toyproject2024.card.application.QueryCardService
import com.minseoklim.toyproject2024.payment.application.CardPaymentApi.CardPaymentRequest
import com.minseoklim.toyproject2024.payment.domain.repository.PaymentRepository
import com.minseoklim.toyproject2024.payment.dto.application.MakeCardPaymentInput
import com.minseoklim.toyproject2024.payment.dto.application.MakeCardPaymentOutput
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class MakeCardPaymentService(
    private val queryCardService: QueryCardService,
    private val paymentRepository: PaymentRepository,
    private val cardPaymentApi: CardPaymentApi
) {
    fun make(
        memberId: Long,
        input: MakeCardPaymentInput
    ): MakeCardPaymentOutput {
        val card = queryCardService.get(memberId, input.cardId)
        val cardPayment = paymentRepository.save(input.toEntity(memberId))

        cardPaymentApi.requestPayment(
            CardPaymentRequest(
                paymentUid = cardPayment.paymentUid.value,
                amount = input.amount,
                cardNumber = card.cardNumber,
                cardExpiry = card.cardExpiry,
                birth = card.birth,
                pwd2digit = card.pwd2digit,
                productName = input.productName
            )
        )

        return MakeCardPaymentOutput.from(cardPayment)
    }
}
