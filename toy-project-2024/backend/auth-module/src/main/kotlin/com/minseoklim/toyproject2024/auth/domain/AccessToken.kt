package com.minseoklim.toyproject2024.auth.domain

import com.minseoklim.toyproject2024.common.domain.BaseTimeEntity
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Index
import jakarta.persistence.Table
import org.hibernate.annotations.SQLRestriction
import org.hibernate.proxy.HibernateProxy

@Entity
@Table(indexes = [Index(columnList = "member_id")])
@SQLRestriction("is_deleted = false")
class AccessToken(
    @Id
    val id: String,
    val memberId: Int,
    val content: String
) : BaseTimeEntity() {
    var isDeleted: Boolean = false
        protected set

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
        other as AccessToken

        return id == other.id
    }

    final override fun hashCode(): Int =
        if (this is HibernateProxy) this.hibernateLazyInitializer.persistentClass.hashCode() else javaClass.hashCode()
}
