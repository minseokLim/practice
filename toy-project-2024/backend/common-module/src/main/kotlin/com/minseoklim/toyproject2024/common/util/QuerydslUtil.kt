package com.minseoklim.toyproject2024.common.util

import com.querydsl.core.types.Predicate
import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.core.types.dsl.SimpleExpression

object QuerydslUtil {
    infix fun <T> SimpleExpression<T>.equal(right: T): BooleanExpression {
        return this.eq(right)
    }

    infix fun BooleanExpression.and(right: Predicate): BooleanExpression {
        return this.and(right)
    }
}
