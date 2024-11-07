package com.minseoklim.toyproject2024.card.dto.ui

import com.minseoklim.toyproject2024.card.dto.application.QueryCardOutput

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
