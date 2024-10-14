package com.minseoklim.modularmonoliths.orders

import org.springframework.context.annotation.Import

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Import(OrderModuleConfiguration::class)
annotation class EnableOrderModule
