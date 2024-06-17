package com.minseoklim.designpattern.facade

class EmpDao(
    employees: Collection<Emp>
) {
    private val db = employees.associateBy { it.id }

    fun select(id: Int): Emp? {
        return db[id]
    }
}
