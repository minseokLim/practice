package com.minseoklim.toyproject2024.common.config

import feign.Retryer
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.concurrent.TimeUnit

@Configuration
@EnableFeignClients(basePackages = ["com.minseoklim"])
class FeignConfig {
    @Bean
    fun retryer(): Retryer {
        return Retryer.Default(100L, TimeUnit.SECONDS.toMillis(1L), 3)
    }
}
