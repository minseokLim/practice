package com.minseoklim.webfluxpractice.config

import com.minseoklim.webfluxpractice.domain.Employee
import com.minseoklim.webfluxpractice.domain.EmployeeRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.RequestPredicates.GET
import org.springframework.web.reactive.function.server.RequestPredicates.PUT
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.RouterFunctions.route
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.ok

@Configuration
class FunctionalConfig {

    @Bean
    fun employeeRepository(): EmployeeRepository {
        return EmployeeRepository()
    }

    @Bean
    fun composedRoutes(): RouterFunction<ServerResponse> {
        return route(GET("/employees/{id}")) {
            ok().body(employeeRepository().findEmployeeById(it.pathVariable("id").toInt()), Employee::class.java)
        }.and(
            route(GET("/employees")) {
                ok().body(employeeRepository().findAllEmployees(), Employee::class.java)
            }
        ).and(
            route(PUT("/employees/{id}")) { req ->
                req.bodyToMono(Employee::class.java).doOnNext {
                    employeeRepository().updateEmployee(it)
                }.then(ok().build())
            }
        )
    }
}
