package com.minseoklim.designpattern.facade

class EmpReportDaoFacade(
    private val empDao: EmpDao,
    private val resumeDao: ResumeDao,
    private val evaluationDao: EvaluationDao
) : EmpReportFacade {
    override fun select(id: Int): EmpReport {
        val emp = empDao.select(id) ?: throw IllegalArgumentException("No employee found for id $id")
        val resume = resumeDao.select(id) ?: throw IllegalArgumentException("No resume found for id $id")
        val evaluation = evaluationDao.select(id) ?: throw IllegalArgumentException("No evaluation found for id $id")

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
