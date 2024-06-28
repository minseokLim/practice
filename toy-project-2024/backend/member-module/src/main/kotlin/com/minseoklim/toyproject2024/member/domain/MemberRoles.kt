package com.minseoklim.toyproject2024.member.domain

import com.minseoklim.toyproject2024.auth.domain.Role
import jakarta.persistence.CollectionTable
import jakarta.persistence.ElementCollection
import jakarta.persistence.Embeddable
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import org.hibernate.proxy.HibernateProxy
import java.util.Objects

@Embeddable
class MemberRoles(
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "member_role", joinColumns = [JoinColumn(name = "member_id")])
    private val values: MutableSet<MemberRole> = mutableSetOf()
) {
    fun addRole(role: Role) {
        values.add(MemberRole(role))
    }

    fun getRoles(): Set<Role> {
        return values.map { it.role }.toSet()
    }

    final override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null) return false
        val oEffectiveClass =
            if (other is HibernateProxy) other.hibernateLazyInitializer.persistentClass else other.javaClass
        val thisEffectiveClass =
            if (this is HibernateProxy) this.hibernateLazyInitializer.persistentClass else this.javaClass
        if (thisEffectiveClass != oEffectiveClass) return false
        other as MemberRoles

        return values == other.values
    }

    final override fun hashCode(): Int = Objects.hash(values)
}
