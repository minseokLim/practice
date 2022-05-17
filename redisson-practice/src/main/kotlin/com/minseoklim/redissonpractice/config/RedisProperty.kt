package com.minseoklim.redissonpractice.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties("redis")
class RedisProperty {
    lateinit var mode: RedisMode
    lateinit var nodes: List<String>

    enum class RedisMode {
        SINGLE, CLUSTER
    }
}
