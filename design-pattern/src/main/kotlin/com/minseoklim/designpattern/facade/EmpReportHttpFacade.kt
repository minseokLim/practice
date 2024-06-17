package com.minseoklim.designpattern.facade

class EmpReportHttpFacade(
    private val empHttpClient: EmpHttpClient,
    private val resumeHttpClient: ResumeHttpClient,
    private val evaluationHttpClient: EvaluationHttpClient
) : EmpReportFacade {
    override fun select(id: Int): EmpReport {
        val emp = empHttpClient.get(id) ?: throw IllegalArgumentException("No employee found for id $id")
        val resume = resumeHttpClient.get(id) ?: throw IllegalArgumentException("No resume found for id $id")
        val evaluation = evaluationHttpClient.get(id) ?: throw IllegalArgumentException("No evaluation found for id $id")

        return EmpReport(
            employeeId = emp.id,
            employeeName = emp.name,
            phone = resume.phone,
            address = resume.address,
            evaluationGrade = evaluation.grade,
            evaluationComment = evaluation.comment
        )
    }
}
