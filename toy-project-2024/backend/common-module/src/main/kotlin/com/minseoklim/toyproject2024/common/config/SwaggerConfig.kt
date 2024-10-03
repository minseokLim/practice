package com.minseoklim.toyproject2024.common.config

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.security.SecurityScheme
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpHeaders

@Configuration
class SwaggerConfig {
    @Bean
    fun openAPI(): OpenAPI {
        return OpenAPI()
            .info(apiInfo())
            .components(apiComponents())
            .security(securityRequirements())
    }

    private fun apiInfo(): Info {
        return Info()
            .title("임민석 2024 토이 프로젝트")
            .description("이것 저것 연습삼아 다 적용해보는 프로젝트")
            .version("0.0.1-SNAPSHOT")
    }

    private fun apiComponents(): Components {
        return Components()
            .addSecuritySchemes(
                "bearerAuth",
                SecurityScheme()
                    .type(SecurityScheme.Type.HTTP)
                    .scheme("bearer")
                    .bearerFormat("JWT")
                    .`in`(SecurityScheme.In.HEADER)
                    .name(HttpHeaders.AUTHORIZATION)
            )
    }

    private fun securityRequirements(): List<SecurityRequirement> {
        return listOf(SecurityRequirement().addList("bearerAuth"))
    }
}
