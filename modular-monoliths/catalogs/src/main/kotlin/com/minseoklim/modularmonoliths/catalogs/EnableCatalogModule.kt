package com.minseoklim.modularmonoliths.catalogs

import org.springframework.context.annotation.Import

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Import(CatalogModuleConfiguration::class)
annotation class EnableCatalogModule
