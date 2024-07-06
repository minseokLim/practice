package com.minseoklim.toyproject2024.common.config

import com.minseoklim.toyproject2024.common.util.PasswordEncodeUtil
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
class EncryptionConfig {

    @Bean
    fun passwordEncodeUtil(): PasswordEncodeUtil {
        PasswordEncodeUtil.init(passwordEncoder())
        return PasswordEncodeUtil
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }
}
