package com.minseoklim.designpattern.facade

class EvaluationHttpClient(
    evaluations: Collection<Evaluation>
) {
    private val http = evaluations.associateBy { it.employeeId }

    fun get(employeeId: Int): Evaluation? {
        return http[employeeId]
    }
}
