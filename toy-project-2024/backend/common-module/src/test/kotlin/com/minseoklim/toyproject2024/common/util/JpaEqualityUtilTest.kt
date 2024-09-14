package com.minseoklim.toyproject2024.common.util

import com.minseoklim.toyproject2024.common.util.JpaEqualityUtil.equalsForEntityAndEmbeddable
import com.minseoklim.toyproject2024.common.util.JpaEqualityUtil.hashCodeForEntity
import com.minseoklim.toyproject2024.test.util.HibernateProxyUtil
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class JpaEqualityUtilTest {
    @Test
    fun equalsForEntityAndEmbeddable() {
        // given
        val obj1 = TestEntity(1)
        val obj2 = TestEntity(1)

        // when
        val result1 = obj1.equalsForEntityAndEmbeddable(obj2) { x, y -> x.id == y.id }

        // then
        assertThat(result1).isTrue

        // given
        val obj3 = TestEntity(2)

        // when
        val result2 = obj1.equalsForEntityAndEmbeddable(obj3) { x, y -> x.id == y.id }

        // then
        assertThat(result2).isFalse

        // when
        val result3 = obj1.equalsForEntityAndEmbeddable(obj1) { x, y -> x.id == y.id }

        // then
        assertThat(result3).isTrue

        // when
        val result4 = obj1.equalsForEntityAndEmbeddable(null) { x, y -> x.id == y.id }

        // then
        assertThat(result4).isFalse

        // when
        val result5 = obj1.equalsForEntityAndEmbeddable(Any()) { x, y -> x.id == y.id }

        // then
        assertThat(result5).isFalse

        // given
        val proxy = HibernateProxyUtil.createHibernateProxy(obj1)

        // when
        val result6 = obj1.equalsForEntityAndEmbeddable(proxy) { x, y -> x.id == y.id }

        // then
        assertThat(result6).isTrue

        // when
        val result7 = proxy.equalsForEntityAndEmbeddable(obj1) { x, y -> x.id == y.id }

        // then
        assertThat(result7).isTrue
    }

    @Test
    fun hashCodeForEntity() {
        // given
        val obj = TestEntity(1)

        // when
        val result1 = obj.hashCodeForEntity()

        // then
        assertThat(result1).isNotNull

        // given
        val proxy = HibernateProxyUtil.createHibernateProxy(obj)

        // when
        val result2 = proxy.hashCodeForEntity()

        // then
        assertThat(result2).isNotNull
    }

    open class TestEntity(
        open val id: Int? = null
    )
}
