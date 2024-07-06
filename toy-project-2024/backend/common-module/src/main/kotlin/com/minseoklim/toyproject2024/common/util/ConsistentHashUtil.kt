package com.minseoklim.toyproject2024.common.util

import java.security.MessageDigest
import java.util.Base64

object ConsistentHashUtil {
    private const val HASH_ALGORITHM = "SHA-256"

    fun hash(input: String): String {
        val md = MessageDigest.getInstance(HASH_ALGORITHM)
        val digest = md.digest(input.toByteArray())
        return Base64.getEncoder().encodeToString(digest)
    }
}
