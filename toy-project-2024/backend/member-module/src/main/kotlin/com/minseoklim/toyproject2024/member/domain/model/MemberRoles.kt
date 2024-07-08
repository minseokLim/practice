package com.minseoklim.toyproject2024.member.domain.model

import com.minseoklim.toyproject2024.common.util.CommonUtil.entityEmbeddableEquals
import jakarta.persistence.CollectionTable
import jakarta.persistence.ElementCollection
import jakarta.persistence.Embeddable
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import java.util.Objects

@Embeddable
class MemberRoles(
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "member_role", joinColumns = [JoinColumn(name = "member_id")])
    protected val values: MutableSet<MemberRole> = mutableSetOf()
) {
    fun addRole(role: Role) {
        values.add(MemberRole(role))
    }

    fun deleteRole(role: Role) {
        values.removeIf { it.role == role }
    }

    fun getRoles(): Set<Role> {
        return values.map { it.role }.toSet()
    }

    final override fun equals(other: Any?): Boolean {
        return this.entityEmbeddableEquals(other) { x, y -> x.values == y.values }
    }

    final override fun hashCode(): Int = Objects.hash(values)
}
