package com.minseoklim.webfluxpractice.ui

import com.minseoklim.webfluxpractice.domain.Employee
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.fail
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.http.HttpStatus
import org.springframework.web.reactive.function.client.WebClient

// assertion이 메인 스레드에서 이뤄지지 않기 때문에 의미가 없었구나...;;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
internal class EmployeeControllerTest {

    @LocalServerPort
    private var port = 0
    private lateinit var client: WebClient

    @BeforeEach
    internal fun setUp() {
        client = WebClient.create("http://localhost:$port")
    }

    @Test
    fun getEmployeeById() {
        val employeeMono = client.get()
            .uri("/employees/{id}", 1)
            .retrieve()
            .bodyToMono(Employee::class.java)

        employeeMono.subscribe {
            println("getEmployeeById 성공")
            println(it)
            assertThat(it).isNotNull
        }

        // subscribe 는 비동기로 처리되기 때문에 1초 기다린다
        Thread.sleep(1000)
    }

    @Test
    fun getAllEmployees() {
        val employeeFlux = client.get()
            .uri("/employees")
            .retrieve()
            .bodyToFlux(Employee::class.java)

        employeeFlux.subscribe {
            println("getAllEmployees 성공")
            println(it)
            assertThat(it).isNotNull
        }

        // subscribe 는 비동기로 처리되기 때문에 1초 기다린다
        Thread.sleep(1000)
    }

    @Test
    fun updateEmployee() {
        val employeeMono = client.put()
            .uri("/employees/{id}", 1)
            .bodyValue(Employee(1, "임민석"))
            .retrieve()
            .onStatus({ !it.equals(HttpStatus.UNAUTHORIZED) }) { fail { "테스트 실패" } }
            .bodyToMono(Employee::class.java)

        employeeMono.subscribe()

        // subscribe 는 비동기로 처리되기 때문에 1초 기다린다
        Thread.sleep(1000)
    }
}
