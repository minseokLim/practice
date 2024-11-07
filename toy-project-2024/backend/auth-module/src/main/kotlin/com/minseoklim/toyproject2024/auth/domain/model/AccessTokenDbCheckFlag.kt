package com.minseoklim.toyproject2024.auth.domain.model

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.TimeToLive
import java.util.concurrent.TimeUnit

@RedisHash("access_token_db_check_flag")
data class AccessTokenDbCheckFlag(
    @Id
    val memberId: Long,

    @TimeToLive(unit = TimeUnit.MILLISECONDS)
    val timeToLive: Long
)
