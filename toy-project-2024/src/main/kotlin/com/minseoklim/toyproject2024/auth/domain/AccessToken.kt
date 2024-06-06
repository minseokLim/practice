package com.minseoklim.toyproject2024.auth.domain

import com.minseoklim.toyproject2024.common.domain.BaseTimeEntity
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Index
import jakarta.persistence.Table

@Entity
@Table(indexes = [Index(columnList = "member_id")])
class AccessToken(
    @Id
    val id: String,
    val memberId: Int,
    val content: String,
) : BaseTimeEntity()
