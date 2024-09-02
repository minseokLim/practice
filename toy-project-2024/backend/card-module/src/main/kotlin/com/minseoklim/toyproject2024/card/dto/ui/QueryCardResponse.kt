package com.minseoklim.toyproject2024.card.dto.ui

import com.minseoklim.toyproject2024.card.dto.application.QueryCardOutput

data class QueryCardResponse private constructor(
    val id: Int,
    val maskedCardNumber: String,
    val issuerName: String
) {
    companion object {
        fun of(output: QueryCardOutput): QueryCardResponse {
            return with(output) {
                QueryCardResponse(
                    id = id,
                    maskedCardNumber = maskedCardNumber,
                    issuerName = issuerName
                )
            }
        }
    }
}
