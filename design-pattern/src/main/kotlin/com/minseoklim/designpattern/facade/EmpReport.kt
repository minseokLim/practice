package com.minseoklim.designpattern.facade

data class EmpReport(
    val employeeId: Int,
    val employeeName: String,
    val phone: String,
    val address: String,
    val evaluationGrade: String,
    val evaluationComment: String
)
