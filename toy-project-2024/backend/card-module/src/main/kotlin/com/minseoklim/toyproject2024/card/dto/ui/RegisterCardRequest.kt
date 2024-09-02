package com.minseoklim.toyproject2024.card.dto.ui

import com.minseoklim.toyproject2024.card.domain.model.Birth
import com.minseoklim.toyproject2024.card.domain.model.CardExpiry
import com.minseoklim.toyproject2024.card.domain.model.CardNumber
import com.minseoklim.toyproject2024.card.domain.model.IssuerName
import com.minseoklim.toyproject2024.card.domain.model.Pwd2digit
import com.minseoklim.toyproject2024.card.dto.application.RegisterCardInput
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern

data class RegisterCardRequest(
    @get:Pattern(regexp = CardNumber.REGEX_STR, message = CardNumber.ERR_MSG)
    val cardNumber: String,

    @get:Pattern(regexp = CardExpiry.REGEX_STR, message = CardExpiry.ERR_MSG)
    val cardExpiry: String,

    @get:Pattern(regexp = Birth.REGEX_STR, message = Birth.ERR_MSG)
    val birth: String,

    @get:Pattern(regexp = Pwd2digit.REGEX_STR, message = Pwd2digit.ERR_MSG)
    val pwd2digit: String,

    @get:NotBlank(message = IssuerName.ERR_MSG)
    val issuerName: String
) {
    fun toInput(): RegisterCardInput {
        return RegisterCardInput(
            cardNumber = cardNumber,
            cardExpiry = cardExpiry,
            birth = birth,
            pwd2digit = pwd2digit,
            issuerName = issuerName
        )
    }
}
