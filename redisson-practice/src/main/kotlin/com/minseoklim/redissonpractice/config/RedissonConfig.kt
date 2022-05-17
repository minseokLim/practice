package com.minseoklim.redissonpractice.config

import org.redisson.Redisson
import org.redisson.api.RedissonClient
import org.redisson.config.Config
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import com.minseoklim.redissonpractice.config.RedisProperty.RedisMode.CLUSTER
import com.minseoklim.redissonpractice.config.RedisProperty.RedisMode.SINGLE

@Configuration
class RedissonConfig {
    @Bean
    fun redissonClient(redisProperty: RedisProperty): RedissonClient {
        val config = Config().apply {
            when (redisProperty.mode) {
                SINGLE -> this.useSingleServer().address = redisProperty.nodes[0]
                CLUSTER -> this.useClusterServers().nodeAddresses = redisProperty.nodes
            }
        }

        return Redisson.create(config)
    }
}
