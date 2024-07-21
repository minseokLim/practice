package com.minseoklim.toyproject2024.auth.config

import com.minseoklim.toyproject2024.auth.filter.JwtFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.cors.CorsConfiguration

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
class SecurityConfig(
    private val jwtFilter: JwtFilter,
    private val oAuth2AuthenticationSuccessHandler: OAuth2AuthenticationSuccessHandler,
    private val oAuth2UserService: OAuth2UserService<OAuth2UserRequest, OAuth2User>
) {
    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http.csrf { it.disable() }
            .cors { corsConfigurer ->
                corsConfigurer.configurationSource {
                    CorsConfiguration().apply {
                        this.allowedOrigins = listOf("*")
                        this.allowedMethods = listOf("*")
                        this.allowedHeaders = listOf("*")
                        this.allowCredentials = false
                        this.maxAge = 3600L
                    }
                }
            }
            .headers { headerConfigurer -> headerConfigurer.frameOptions { it.sameOrigin() } }
            .logout { it.disable() }
            .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
            .authorizeHttpRequests {
                it
                    .requestMatchers(HttpMethod.POST, "/login", "/logout", "/refresh-token", "/members").permitAll()
                    .requestMatchers(HttpMethod.GET, "/", "/login", "/join").permitAll()
                    .requestMatchers("/ws/**").permitAll()
                    .requestMatchers(
                        "/h2-console/**",
                        "/swagger-ui/**",
                        "/swagger-ui.html",
                        "/swagger-resources/**",
                        "/v3/api-docs/**"
                    ).permitAll()
                    .anyRequest().authenticated()
            }
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter::class.java)
            .exceptionHandling {
                it.authenticationEntryPoint { _, response, _ ->
                    response.sendError(401, HttpStatus.UNAUTHORIZED.name)
                }
            }
            .oauth2Login { oAuth2LoginConfigurer ->
                oAuth2LoginConfigurer
                    .successHandler(oAuth2AuthenticationSuccessHandler)
                    .userInfoEndpoint { userInfoEndpointConfig ->
                        userInfoEndpointConfig.userService {
                            oAuth2UserService.loadUser(it)
                        }
                    }
            }

        return http.build()
    }
}
