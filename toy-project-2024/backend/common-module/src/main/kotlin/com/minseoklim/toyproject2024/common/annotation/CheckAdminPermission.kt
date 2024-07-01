package com.minseoklim.toyproject2024.common.annotation

import org.springframework.security.access.prepost.PreAuthorize

@Target(AnnotationTarget.FUNCTION, AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@PreAuthorize("hasRole('ADMIN')")
annotation class CheckAdminPermission
