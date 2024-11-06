package com.minseoklim.toyproject2024.feed.domain.model

import com.minseoklim.toyproject2024.common.util.JpaEqualityUtil.equalsForEntity
import com.minseoklim.toyproject2024.common.util.JpaEqualityUtil.hashCodeForEntity
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.time.LocalDateTime

@Entity
class FeedAttachedFile(
    val fileName: String,
    val fileLinkUrl: String,
    val createdDateTime: LocalDateTime = LocalDateTime.now()
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    final override fun equals(other: Any?): Boolean {
        return this.equalsForEntity(other) { x, y -> x.id == y.id }
    }

    final override fun hashCode(): Int = this.hashCodeForEntity()
}
