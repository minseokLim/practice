package com.minseoklim.toyproject2024.member.domain

import com.minseoklim.toyproject2024.auth.domain.Role
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
    var password: Password = Password(password)
    var name: Name = Name(name)
    var email: Email = Email(email)
    val memberRoles = MemberRoles().apply { addRole(Role.MEMBER) }
    var isDeleted: Boolean = false

    fun addRole(role: Role) {
        memberRoles.addRole(role)
    }

    fun getRoles(): Set<Role> {
        return memberRoles.getRoles()
    }

    fun update(other: Member) {
        password = other.password
        name = other.name
        email = other.email
    }

    fun delete() {
        isDeleted = true
    }
}
