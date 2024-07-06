package com.minseoklim.toyproject2024.common.util

import org.springframework.security.crypto.encrypt.TextEncryptor

object TextEncryptUtil {
    private lateinit var textEncryptor: TextEncryptor

    fun init(textEncryptor: TextEncryptor) {
        TextEncryptUtil.textEncryptor = textEncryptor
    }

    fun encrypt(text: String): String {
        return textEncryptor.encrypt(text)
    }

    fun decrypt(text: String): String {
        return textEncryptor.decrypt(text)
    }
}
