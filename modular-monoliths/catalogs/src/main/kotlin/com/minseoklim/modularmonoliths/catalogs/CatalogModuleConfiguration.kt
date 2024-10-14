package com.minseoklim.modularmonoliths.catalogs

import com.minseoklim.modularmonoliths.commons.module.info.ModuleInfoProperties
import mu.KLogging
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@Configuration
@ComponentScan(basePackageClasses = [CatalogModuleConfiguration::class])
@EnableConfigurationProperties
class CatalogModuleConfiguration : ApplicationRunner {
    @Bean
    @ConfigurationProperties("monoliths.catalogs.info")
    fun catalogModuleInfoProperties(): ModuleInfoProperties {
        return ModuleInfoProperties()
    }

    override fun run(args: ApplicationArguments?) {
        logger.info("Started ${catalogModuleInfoProperties()}")
    }

    companion object : KLogging()
}
