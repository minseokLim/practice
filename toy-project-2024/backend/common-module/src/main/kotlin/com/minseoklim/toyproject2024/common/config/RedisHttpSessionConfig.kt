package com.minseoklim.toyproject2024.common.config

import org.springframework.context.annotation.Configuration
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession

@Configuration
@EnableRedisHttpSession
class RedisHttpSessionConfig
