package com.minseoklim.redissonpractice.application

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import com.minseoklim.redissonpractice.util.KeyValueStore

@SpringBootTest
internal class TestServiceTest {

    @Autowired
    private lateinit var testService: TestService

    @Autowired
    private lateinit var keyValueStore: KeyValueStore

    @Test
    fun test() {
        assertThrows<IllegalArgumentException> {
            testService.test()
        }

        // redis rollback 지원 안함
//        assertThat(keyValueStore.getValue<String>("test")).isEmpty
        assertThat(keyValueStore.getValue<String>("test")).isPresent
    }
}
