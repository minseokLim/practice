package com.minseoklim.toyproject2024.common.config

import com.minseoklim.toyproject2024.common.util.PasswordEncodeUtil
import com.minseoklim.toyproject2024.common.util.TextEncryptUtil
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.encrypt.Encryptors
import org.springframework.security.crypto.encrypt.TextEncryptor
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
class EncryptionConfig(
    @Value("\${encryptor.password}")
    private val encryptorPassword: String,
    @Value("\${encryptor.salt}")
    private val encryptorSalt: String
) {
    @Bean
    fun passwordEncodeUtil(): PasswordEncodeUtil {
        PasswordEncodeUtil.init(passwordEncoder())
        return PasswordEncodeUtil
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun textEncryptUtil(): TextEncryptUtil {
        TextEncryptUtil.init(textEncryptor())
        return TextEncryptUtil
    }

    @Bean
    fun textEncryptor(): TextEncryptor {
        return Encryptors.text(encryptorPassword, encryptorSalt)
    }
}
