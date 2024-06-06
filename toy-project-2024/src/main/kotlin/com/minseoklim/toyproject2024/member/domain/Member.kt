package com.minseoklim.toyproject2024.member.domain

import com.minseoklim.toyproject2024.common.domain.BaseTimeEntity
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.persistence.UniqueConstraint

@Entity
@Table(uniqueConstraints = [UniqueConstraint(columnNames = ["login_id"])])
class Member(
    loginId: String,
    password: String,
    name: String,
    email: String
) : BaseTimeEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null
    val loginId: LoginId = LoginId(loginId)
    val password: Password = Password(password)
    val name: Name = Name(name)
    val email: Email = Email(email)
}
