package com.minseoklim.toyproject2024.common.util

import com.minseoklim.toyproject2024.common.util.JpaEqualityUtil.equalsForEmbeddable
import com.minseoklim.toyproject2024.common.util.JpaEqualityUtil.equalsForEntity
import com.minseoklim.toyproject2024.common.util.JpaEqualityUtil.hashCodeForEntity
import com.minseoklim.toyproject2024.test.util.HibernateProxyUtil
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class JpaEqualityUtilTest {
    @Test
    fun equalsForEntity() {
        // given
        val obj1 = TestEntity(1)
        val obj2 = TestEntity(1)

        // when
        val result1 = obj1.equalsForEntity(obj2) { x, y -> x.id == y.id }

        // then
        assertThat(result1).isTrue

        // given
        val obj3 = TestEntity(2)

        // when
        val result2 = obj1.equalsForEntity(obj3) { x, y -> x.id == y.id }

        // then
        assertThat(result2).isFalse

        // when
        val result3 = obj1.equalsForEntity(obj1) { x, y -> x.id == y.id }

        // then
        assertThat(result3).isTrue

        // when
        val result4 = obj1.equalsForEntity(null) { x, y -> x.id == y.id }

        // then
        assertThat(result4).isFalse

        // when
        val result5 = obj1.equalsForEntity(Any()) { x, y -> x.id == y.id }

        // then
        assertThat(result5).isFalse

        // given
        val proxy = HibernateProxyUtil.createHibernateProxy(obj1)

        // when
        val result6 = obj1.equalsForEntity(proxy) { x, y -> x.id == y.id }

        // then
        assertThat(result6).isTrue

        // when
        val result7 = proxy.equalsForEntity(obj1) { x, y -> x.id == y.id }

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

    @Test
    fun equalsForEmbeddable() {
        // given
        val obj1 = TestValueClass(1)
        val obj2 = TestValueClass(1)

        // when
        val result1 = obj1.equalsForEmbeddable(obj2) { x, y -> x.value == y.value }

        // then
        assertThat(result1).isTrue

        // given
        val obj3 = TestValueClass(2)

        // when
        val result2 = obj1.equalsForEmbeddable(obj3) { x, y -> x.value == y.value }

        // then
        assertThat(result2).isFalse

        // when
        val result3 = obj1.equalsForEmbeddable(obj1) { x, y -> x.value == y.value }

        // then
        assertThat(result3).isTrue

        // when
        val result4 = obj1.equalsForEmbeddable(null) { x, y -> x.value == y.value }

        // then
        assertThat(result4).isFalse

        // when
        val result5 = obj1.equalsForEmbeddable(Any()) { x, y -> x.value == y.value }

        // then
        assertThat(result5).isFalse
    }

    open class TestEntity(
        open val id: Long? = null
    )

    class TestValueClass(
        val value: Int
    )
}
