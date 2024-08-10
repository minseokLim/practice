package com.minseoklim.toyproject2024.payment.ui

import com.minseoklim.toyproject2024.common.exception.BadRequestException
import com.minseoklim.toyproject2024.payment.application.PaymentServiceHelper
import com.minseoklim.toyproject2024.payment.domain.model.VerifiedPayment
import com.minseoklim.toyproject2024.payment.domain.repository.PaymentRepository
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
class PaymentViewController(
    private val paymentRepository: PaymentRepository
) {
    @GetMapping("/verified-payment")
    fun verifiedPayment(@RequestParam paymentId: Int, model: Model): String {
        val payment = PaymentServiceHelper.getPayment(paymentRepository, paymentId)
//        val payment = VerifiedPayment(100, "테스트 상품", 1)
        if (payment !is VerifiedPayment) {
            throw BadRequestException("NOT_VERIFIED_PAYMENT", "인증 결제 정보가 아닙니다.")
        }

        model.addAttribute("payment", payment)

        return "verifiedPayment"
    }
}
