package com.minseoklim.toyproject2024.common.annotation

import io.swagger.v3.oas.annotations.Parameter

@Target(AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
@Parameter(required = false, hidden = true)
annotation class MemberId
