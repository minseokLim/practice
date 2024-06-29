package com.minseoklim.toyproject2024.util

import org.springframework.beans.factory.annotation.Value
import org.springframework.security.crypto.encrypt.Encryptors
import org.springframework.security.crypto.encrypt.TextEncryptor
import org.springframework.stereotype.Component

@Component
class EncryptionUtil(
    @Value("\${encryptor.password}")
    private val encryptorPassword: String,
    @Value("\${encryptor.salt}")
    private val encryptorSalt: String
) : TextEncryptor by Encryptors.text(encryptorPassword, encryptorSalt)
