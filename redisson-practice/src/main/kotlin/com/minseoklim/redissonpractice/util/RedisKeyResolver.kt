package com.minseoklim.redissonpractice.util

object RedisKeyResolver {
    private const val KEY_DELIMITER = ":"

    fun getKey(id: Any, domain: String, vararg subDomains: String): String {
        return listOf(domain, *subDomains, id.toString()).joinToString(KEY_DELIMITER)
    }
}
