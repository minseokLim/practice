package com.minseoklim.toyproject2024.test

import com.minseoklim.toyproject2024.test.util.DatabaseCleanup
import com.minseoklim.toyproject2024.test.util.RedisCleanup
import io.restassured.RestAssured
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.test.context.ActiveProfiles

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
abstract class AcceptanceTest {
    @LocalServerPort
    protected var port = 0

    @Autowired
    private lateinit var databaseCleanup: DatabaseCleanup

    @Autowired
    private lateinit var redisCleanup: RedisCleanup

    @BeforeEach
    fun setUp() {
        RestAssured.port = port
        databaseCleanup.execute()
        redisCleanup.execute()
    }
}
