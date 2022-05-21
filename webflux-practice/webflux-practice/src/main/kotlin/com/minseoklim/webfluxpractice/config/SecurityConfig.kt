package com.minseoklim.webfluxpractice.config

import org.springframework.context.annotation.Bean
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.web.server.SecurityWebFilterChain

@EnableWebFluxSecurity
class SecurityConfig {

    @Bean
    fun securityWebFilterChain(
        http: ServerHttpSecurity
    ): SecurityWebFilterChain {
        http.csrf().disable()
            .authorizeExchange()
            .pathMatchers(HttpMethod.PUT, "/employees").hasRole("ADMIN")
            .pathMatchers("/**").permitAll()
            .and()
            .httpBasic()

        return http.build()
    }
}
