package com.minseoklim.toyproject2024.util

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.security.Key
import java.security.spec.AlgorithmParameterSpec
import java.util.Base64
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

@Component
class AesUtil(
    @Value("\${aes.secret}")
    private val secret: String,
    @Value("\${aes.iv}")
    private val iv: String
) {
    private val keySpec: Key = SecretKeySpec(secret.toByteArray(), KEY_ALGORITHM)
    private val algorithmParameterSpec: AlgorithmParameterSpec = IvParameterSpec(iv.toByteArray())

    fun encrypt(input: String): String {
        val cipher = Cipher.getInstance(CIPHER_PADDING)
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, algorithmParameterSpec)

        val encrypted = cipher.doFinal(input.toByteArray())
        return Base64.getEncoder().encodeToString(encrypted)
    }

    fun decrypt(input: String): String {
        val cipher = Cipher.getInstance(CIPHER_PADDING)
        cipher.init(Cipher.DECRYPT_MODE, keySpec, algorithmParameterSpec)

        val decoded = Base64.getDecoder().decode(input)
        val decrypted = cipher.doFinal(decoded)

        return String(decrypted)
    }

    companion object {
        private const val KEY_ALGORITHM = "AES"
        private const val CIPHER_PADDING = "$KEY_ALGORITHM/CBC/PKCS5Padding"
    }
}
