package com.minseoklim.designpattern.facade

class EvaluationDao(
    evaluations: Collection<Evaluation>
) {
    private val db = evaluations.associateBy { it.employeeId }

    fun select(employeeId: Int): Evaluation? {
        return db[employeeId]
    }
}
