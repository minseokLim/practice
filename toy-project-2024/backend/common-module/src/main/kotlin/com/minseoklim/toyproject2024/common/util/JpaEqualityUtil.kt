package com.minseoklim.toyproject2024.common.util

import org.hibernate.proxy.HibernateProxy

object JpaEqualityUtil {
    /**
     * Entity 클래스에 대한 equals 메서드 처리
     */
    fun <T : Any> T.equalsForEntity(
        other: Any?,
        finalPredicate: (x: T, y: T) -> Boolean
    ): Boolean {
        if (this === other) return true
        if (other == null) return false
        val oEffectiveClass =
            if (other is HibernateProxy) other.hibernateLazyInitializer.persistentClass else other.javaClass
        val thisEffectiveClass =
            if (this is HibernateProxy) this.hibernateLazyInitializer.persistentClass else this.javaClass
        if (thisEffectiveClass != oEffectiveClass) return false
        other as T

        return finalPredicate(this, other)
    }

    /**
     * Entity 클래스에 대한 hashCode 메서드 처리
     */
    fun <T : Any> T.hashCodeForEntity(): Int {
        return if (this is HibernateProxy) this.hibernateLazyInitializer.persistentClass.hashCode() else javaClass.hashCode()
    }

    /**
     * Embeddable 클래스에 대한 equals 메서드 처리
     */
    fun <T : Any> T.equalsForEmbeddable(
        other: Any?,
        finalPredicate: (x: T, y: T) -> Boolean
    ): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as T

        return finalPredicate(this, other)
    }
}
