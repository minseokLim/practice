package com.minseoklim.designpattern.facade

class ResumeDao(
    resumes: Collection<Resume>
) {
    private val db = resumes.associateBy { it.employeeId }

    fun select(id: Int): Resume? {
        return db[id]
    }
}
