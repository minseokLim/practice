package com.minseoklim.webfluxpractice.ui

import com.minseoklim.webfluxpractice.domain.Employee
import com.minseoklim.webfluxpractice.domain.EmployeeRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

// @RestController
// @RequestMapping("/employees")
class EmployeeController(
    private val employeeRepository: EmployeeRepository
) {
    @GetMapping("/{id}")
    fun getEmployeeById(@PathVariable id: Int): Mono<Employee> {
        return employeeRepository.findEmployeeById(id)
    }

    @GetMapping
    fun getAllEmployees(): Flux<Employee> {
        return employeeRepository.findAllEmployees()
    }

    @PutMapping("/{id}")
    fun updateEmployee(@RequestBody employee: Employee): Mono<Employee> {
        return employeeRepository.updateEmployee(employee)
    }
}
