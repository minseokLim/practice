package com.minseoklim.toyproject2024.common.config

import com.minseoklim.toyproject2024.common.util.PasswordEncodeUtil
import com.minseoklim.toyproject2024.common.util.Sha512PasswordEncoder
import com.minseoklim.toyproject2024.common.util.TextEncryptUtil
import org.springframework.beans.factory.InitializingBean
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
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
    fun initUtils(): InitializingBean {
        return InitializingBean {
            PasswordEncodeUtil.init(passwordEncoder())
            TextEncryptUtil.init(textEncryptor())
        }
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return Sha512PasswordEncoder()
    }

    @Bean
    fun textEncryptor(): TextEncryptor {
        return Encryptors.text(encryptorPassword, encryptorSalt)
    }
}
