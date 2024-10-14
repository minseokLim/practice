package com.minseoklim.modularmonoliths.shipments

import org.springframework.context.annotation.Import

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Import(ShipmentModuleConfiguration::class)
annotation class EnableShipmentModule
