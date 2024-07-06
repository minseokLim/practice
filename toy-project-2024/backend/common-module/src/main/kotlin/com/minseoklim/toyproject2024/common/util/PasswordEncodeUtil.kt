package com.minseoklim.toyproject2024.common.util

import org.springframework.security.crypto.password.PasswordEncoder

object PasswordEncodeUtil {
    private lateinit var passwordEncoder: PasswordEncoder

    fun init(passwordEncoder: PasswordEncoder) {
        PasswordEncodeUtil.passwordEncoder = passwordEncoder
    }

    fun encodePassword(password: String): String {
        return passwordEncoder.encode(password)
    }
}
