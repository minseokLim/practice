package com.minseoklim.toyproject2024.auth.domain.model

import com.minseoklim.toyproject2024.common.domain.BaseTimeEntity
import com.minseoklim.toyproject2024.common.util.JpaEqualityUtil.equalsForEntity
import com.minseoklim.toyproject2024.common.util.JpaEqualityUtil.hashCodeForEntity
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Index
import jakarta.persistence.Table
import org.hibernate.annotations.SQLRestriction

@Entity
@Table(indexes = [Index(columnList = "member_id")])
@SQLRestriction("is_deleted = false")
class Token(
    @Id
    val id: String,
    val memberId: Long,
    val accessToken: String,
    val refreshToken: String
) : BaseTimeEntity() {
    var isDeleted: Boolean = false
        protected set

    fun delete() {
        isDeleted = true
    }

    final override fun equals(other: Any?): Boolean {
        return this.equalsForEntity(other) { x, y -> x.id == y.id }
    }

    final override fun hashCode(): Int = this.hashCodeForEntity()
}
