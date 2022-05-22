package com.minseoklim.webfluxpractice.config

import com.minseoklim.webfluxpractice.domain.Employee
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.reactive.server.WebTestClient

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
internal class FunctionalConfigTest {

    @Autowired
    private lateinit var functionalConfig: FunctionalConfig

    @Test
    fun test() {
        val client = WebTestClient.bindToRouterFunction(functionalConfig.composedRoutes()).build()

        val employee = client.get()
            .uri("/employees/{id}", 1)
            .exchange()
            .expectStatus()
            .isOk
            .expectBody(Employee::class.java)
            .returnResult()
            .responseBody

        assertThat(employee).isNotNull

        val employees = client.get()
            .uri("/employees")
            .exchange()
            .expectStatus()
            .isOk
            .expectBodyList(Employee::class.java)
            .returnResult()
            .responseBody

        assertThat(employees).isNotEmpty

        // WebTestClient는 Security를 타지 않는 걸로 보임
        client.put()
            .uri("/employees/{id}", 1)
            .bodyValue(Employee(1, "임민석"))
            .exchange()
            .expectStatus()
            .isOk
    }
}
