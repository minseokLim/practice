package com.minseoklim.webfluxpractice.domain

import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

// @Repository
class EmployeeRepository {
    private val employeeData = mutableMapOf<Int, Employee>()

    init {
        for (i in 1..10) {
            employeeData[i] = Employee(i, "Employee $i")
        }
    }

    fun findEmployeeById(id: Int): Mono<Employee> {
        val employee = employeeData[id] ?: throw IllegalArgumentException()
        return Mono.just(employee)
    }

    fun findAllEmployees(): Flux<Employee> {
        return Flux.fromIterable(employeeData.values)
    }

    fun updateEmployee(employee: Employee): Mono<Employee> {
        employeeData[employee.id] = employee
        return Mono.just(employee)
    }
}
