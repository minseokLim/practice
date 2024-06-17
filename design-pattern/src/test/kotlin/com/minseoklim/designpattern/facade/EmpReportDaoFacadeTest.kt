package com.minseoklim.designpattern.facade

import org.junit.jupiter.api.Test

class EmpReportDaoFacadeTest {

    @Test
    fun select() {
        // given
        val empDao = EmpDao(
            listOf(
                Emp(1, "Alice"),
                Emp(2, "Bob")
            )
        )
        val resumeDao = ResumeDao(
            listOf(
                Resume(1, "01012345678", "서울시 강북구"),
                Resume(2, "01087654321", "서울시 강남구")
            )
        )
        val evaluationDao = EvaluationDao(
            listOf(
                Evaluation(1, "A", "Good"),
                Evaluation(2, "B", "Bad")
            )
        )
        val empReportDaoFacade = EmpReportDaoFacade(empDao, resumeDao, evaluationDao)

        // when
        val empReport = empReportDaoFacade.select(1)

        // then
        assert(empReport.employeeId == 1)
        assert(empReport.employeeName == "Alice")
        assert(empReport.phone == "01012345678")
        assert(empReport.address == "서울시 강북구")
        assert(empReport.evaluationGrade == "A")
        assert(empReport.evaluationComment == "Good")
    }
}
