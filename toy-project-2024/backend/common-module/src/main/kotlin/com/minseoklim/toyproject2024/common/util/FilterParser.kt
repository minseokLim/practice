package com.minseoklim.toyproject2024.common.util

import com.minseoklim.toyproject2024.common.domain.type.ErrorCode
import com.minseoklim.toyproject2024.common.exception.BadRequestException
import com.minseoklim.toyproject2024.common.util.QuerydslUtil.and
import com.querydsl.core.types.Predicate
import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.core.types.dsl.Expressions

abstract class FilterParser {
    fun parse(filter: String?): Predicate {
        if (filter == null) {
            return Expressions.TRUE
        }
        if (!filterRegex.matches(filter)) {
            throw BadRequestException(ErrorCode.INVALID_FILTER_FORMAT)
        }

        val predicates = filter.removePrefix("[").removeSuffix("]").split(",")
        return predicates.map { predicate ->
            val (key, value) = predicate.split(":").map { it.trim() }
            createBooleanExpression(key, value)
        }.reduce { acc, booleanExpression ->
            acc and booleanExpression
        }
    }

    protected abstract fun createBooleanExpression(
        key: String,
        value: String
    ): BooleanExpression

    companion object {
        private val filterRegex = "\\[.+:.+(,.+:.+)*]".toRegex()
    }
}
