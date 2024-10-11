package com.minseoklim.toyproject2024.member.dto.ui

import com.minseoklim.toyproject2024.member.dto.application.QueryMemberOutput

data class SimpleQueryMemberResponse private constructor(
    val id: Int,
    val name: String
) {
    companion object {
        fun from(output: QueryMemberOutput): SimpleQueryMemberResponse {
            return SimpleQueryMemberResponse(
                id = output.id,
                name = output.name
            )
        }
    }
}
