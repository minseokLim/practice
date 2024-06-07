package com.minseoklim.toyproject2024.auth.domain

import com.minseoklim.toyproject2024.common.domain.BaseTimeEntity
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Index
import jakarta.persistence.Table
import org.hibernate.annotations.SQLRestriction

@Entity
@Table(indexes = [Index(columnList = "member_id")])
@SQLRestriction("is_deleted = false")
class AccessToken(
    @Id
    val id: String,
    val memberId: Int,
    val content: String,
    var isDeleted: Boolean = false
) : BaseTimeEntity() {
    fun delete() {
        isDeleted = true
    }
}
