package com.minseoklim.toyproject2024.test.config

import jakarta.annotation.PostConstruct
import jakarta.annotation.PreDestroy
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import redis.embedded.RedisServer
import java.io.BufferedReader
import java.io.InputStreamReader

@Configuration
class TestRedisConfiguration(
    @Value("\${spring.data.redis.port}")
    private val redisPort: Int
) {
    private val redisServer = RedisServer(redisPort)

    @PostConstruct
    fun postConstruct() {
        getRedisProcessIdUsingPort(redisPort)?.let {
            killProcess(it)
        }
        redisServer.start()
    }

    @PreDestroy
    fun preDestroy() {
        redisServer.stop()
    }

    /**
     * 디버깅 모드로 테스트 코드를 실행하다가 강제 종료할 경우, preDestroy() 메서드가 호출되지 않아 Redis 서버가 종료되지 않는 문제가 발생할 수 있다.
     * 이를 대비하여 포트를 통해 해당 프로세스를 찾아 종료시키는 메서드를 추가하였다.
     */
    private fun getRedisProcessIdUsingPort(port: Int): String? {
        val process = ProcessBuilder("lsof", "-i", ":$port").start()
        val reader = BufferedReader(InputStreamReader(process.inputStream))
        reader.use {
            val lines = it.readLines()
            if (lines.size > 1) { // Skip the header line
                val parts = lines[1].split("\\s+".toRegex())
                if (parts[0].startsWith("redis")) {
                    return parts[1] // The PID is usually the second part
                }
            }
        }
        return null
    }

    private fun killProcess(pid: String) {
        ProcessBuilder("kill", "-9", pid).start().waitFor()
        println("Process $pid has been killed.")
    }
}
