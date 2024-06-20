package com.minseoklim.toyproject2024.member.domain

import com.minseoklim.toyproject2024.auth.domain.Role
import com.minseoklim.toyproject2024.common.domain.BaseTimeEntity
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.persistence.UniqueConstraint
import org.hibernate.proxy.HibernateProxy

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

    final var password: Password = Password(password)
        private set

    final var name: Name = Name(name)
        private set

    final var email: Email = Email(email)
        private set

    val memberRoles: MemberRoles = MemberRoles().apply { addRole(Role.MEMBER) }

    final var isDeleted: Boolean = false
        private set

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

    final override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null) return false
        val oEffectiveClass =
            if (other is HibernateProxy) other.hibernateLazyInitializer.persistentClass else other.javaClass
        val thisEffectiveClass =
            if (this is HibernateProxy) this.hibernateLazyInitializer.persistentClass else this.javaClass
        if (thisEffectiveClass != oEffectiveClass) return false
        other as Member

        return id != null && id == other.id
    }

    final override fun hashCode(): Int =
        if (this is HibernateProxy) this.hibernateLazyInitializer.persistentClass.hashCode() else javaClass.hashCode()
}
