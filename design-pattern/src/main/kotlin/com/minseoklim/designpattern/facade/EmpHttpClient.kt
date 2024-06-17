package com.minseoklim.designpattern.facade

class EmpHttpClient(
    employees: Collection<Emp>
) {
    private val http = employees.associateBy { it.id }

    fun get(id: Int): Emp? {
        return http[id]
    }
}
