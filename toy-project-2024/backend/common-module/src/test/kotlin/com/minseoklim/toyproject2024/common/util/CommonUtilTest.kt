package com.minseoklim.toyproject2024.common.util

import com.minseoklim.toyproject2024.common.util.CommonUtil.entityEmbeddableEquals
import com.minseoklim.toyproject2024.common.util.CommonUtil.entityHashCode
import com.minseoklim.toyproject2024.test.util.HibernateProxyUtil
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CommonUtilTest {

    @Test
    fun entityEmbeddableEquals() {
        // given
        val obj1 = TestEntity(1)
        val obj2 = TestEntity(1)

        // when
        val result1 = obj1.entityEmbeddableEquals(obj2) { x, y -> x.id == y.id }

        // then
        assertThat(result1).isTrue

        // given
        val obj3 = TestEntity(2)

        // when
        val result2 = obj1.entityEmbeddableEquals(obj3) { x, y -> x.id == y.id }

        // then
        assertThat(result2).isFalse

        // when
        val result3 = obj1.entityEmbeddableEquals(obj1) { x, y -> x.id == y.id }

        // then
        assertThat(result3).isTrue

        // when
        val result4 = obj1.entityEmbeddableEquals(null) { x, y -> x.id == y.id }

        // then
        assertThat(result4).isFalse

        // when
        val result5 = obj1.entityEmbeddableEquals(Any()) { x, y -> x.id == y.id }

        // then
        assertThat(result5).isFalse

        // given
        val proxy = HibernateProxyUtil.createHibernateProxy(obj1)

        // when
        val result6 = obj1.entityEmbeddableEquals(proxy) { x, y -> x.id == y.id }

        // then
        assertThat(result6).isTrue

        // when
        val result7 = proxy.entityEmbeddableEquals(obj1) { x, y -> x.id == y.id }

        // then
        assertThat(result7).isTrue
    }

    @Test
    fun entityHashCode() {
        // given
        val obj = TestEntity(1)

        // when
        val result1 = obj.entityHashCode()

        // then
        assertThat(result1).isNotNull

        // given
        val proxy = HibernateProxyUtil.createHibernateProxy(obj)

        // when
        val result2 = proxy.entityHashCode()

        // then
        assertThat(result2).isNotNull
    }

    open class TestEntity(
        open val id: Int? = null
    )
}
