package com.minseoklim.designpattern.facade

class ResumeHttpClient(
    resumes: Collection<Resume>
) {
    private val http = resumes.associateBy { it.employeeId }

    fun get(id: Int): Resume? {
        return http[id]
    }
}
