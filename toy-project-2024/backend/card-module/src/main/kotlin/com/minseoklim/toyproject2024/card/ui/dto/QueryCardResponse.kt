package com.minseoklim.toyproject2024.card.ui.dto

import com.minseoklim.toyproject2024.card.application.dto.QueryCardOutput

data class QueryCardResponse private constructor(
    val id: Long,
    val maskedCardNumber: String,
    val issuerName: String
) {
    companion object {
        fun from(output: QueryCardOutput): QueryCardResponse {
            return QueryCardResponse(
                id = output.id,
                maskedCardNumber = output.maskedCardNumber,
                issuerName = output.issuerName
            )
        }
    }
}
