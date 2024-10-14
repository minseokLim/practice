package com.minseoklim.modularmonoliths.orders

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
@ComponentScan(basePackageClasses = [OrderModuleConfiguration::class])
@EnableConfigurationProperties
class OrderModuleConfiguration : ApplicationRunner {
    @Bean
    @ConfigurationProperties("monoliths.orders.info")
    fun orderModuleInfoProperties(): ModuleInfoProperties {
        return ModuleInfoProperties()
    }

    override fun run(args: ApplicationArguments?) {
        logger.info("Started ${orderModuleInfoProperties()}")
    }

    companion object : KLogging()
}
