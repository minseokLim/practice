package com.minseoklim.toyproject2024.test.util

import io.mockk.every
import io.mockk.mockk
import org.hibernate.proxy.HibernateProxy
import org.hibernate.proxy.LazyInitializer
import org.springframework.cglib.proxy.Enhancer
import org.springframework.cglib.proxy.MethodInterceptor
import org.springframework.cglib.proxy.MethodProxy
import java.lang.reflect.Method

object HibernateProxyUtil {
    inline fun <reified T : Any> createHibernateProxy(target: T): T {
        val lazyInitializer = mockk<LazyInitializer>()
        every { lazyInitializer.persistentClass } returns T::class.java

        val enhancer = Enhancer()
        enhancer.setSuperclass(target::class.java)
        enhancer.setInterfaces(arrayOf(HibernateProxy::class.java))
        enhancer.setCallback(HibernateMethodInterceptor(target, lazyInitializer))

        return enhancer.create() as T
    }

    class HibernateMethodInterceptor<T>(
        private val target: T,
        private val lazyInitializer: LazyInitializer
    ) : MethodInterceptor {
        override fun intercept(obj: Any, method: Method, args: Array<Any>?, proxy: MethodProxy): Any {
            return if (method.declaringClass == HibernateProxy::class.java && method.name == "getHibernateLazyInitializer") {
                lazyInitializer
            } else {
                try {
                    method.isAccessible = true
                    method.invoke(target, *(args ?: emptyArray()))
                } catch (e: NoSuchMethodException) {
                    proxy.invokeSuper(obj, args)
                }
            }
        }
    }
}
