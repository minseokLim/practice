package com.minseoklim.toyproject2024.member.domain

import com.minseoklim.toyproject2024.auth.domain.Role
import jakarta.persistence.CollectionTable
import jakarta.persistence.ElementCollection
import jakarta.persistence.Embeddable
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn

@Embeddable
class MemberRoles(
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "member_role", joinColumns = [JoinColumn(name = "member_id")])
    val values: MutableSet<MemberRole> = mutableSetOf()
) {
    fun addRole(role: Role) {
        values.add(MemberRole(role))
    }

    fun getRoles(): Set<Role> {
        return values.map { it.role }.toSet()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MemberRoles

        return values == other.values
    }

    override fun hashCode(): Int {
        return values.hashCode()
    }
}
