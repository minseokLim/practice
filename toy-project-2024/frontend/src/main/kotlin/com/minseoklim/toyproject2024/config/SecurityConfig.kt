package com.minseoklim.toyproject2024.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration
class SecurityConfig(
    private val oauth2AuthenticationSuccessHandler: OAuth2AuthenticationSuccessHandler
) {
    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http.formLogin { it.disable() }
            .oauth2Login {
                it.loginPage("/login")
                    .successHandler(oauth2AuthenticationSuccessHandler)
            }

        return http.build()
    }
}
