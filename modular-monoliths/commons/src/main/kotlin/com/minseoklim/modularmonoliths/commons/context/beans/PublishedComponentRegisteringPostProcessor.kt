package com.minseoklim.modularmonoliths.commons.context.beans

import org.springframework.aop.support.AopUtils
import org.springframework.beans.factory.NoSuchBeanDefinitionException
import org.springframework.beans.factory.config.BeanPostProcessor
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory
import org.springframework.core.annotation.AnnotationUtils
import org.springframework.core.type.AnnotatedTypeMetadata
import org.springframework.util.ClassUtils
import kotlin.reflect.KClass

abstract class PublishedComponentRegisteringPostProcessor(
    private val beanFactory: ConfigurableListableBeanFactory?
) : BeanPostProcessor {
    private val parentBeanFactory: ConfigurableListableBeanFactory? = getParentBeanFactory(beanFactory)

    private fun getParentBeanFactory(beanFactory: ConfigurableListableBeanFactory?): ConfigurableListableBeanFactory? {
        if (beanFactory == null) {
            return null
        }

        val parent = beanFactory.parentBeanFactory
        return if (parent is ConfigurableListableBeanFactory) parent else null
    }

    override fun postProcessAfterInitialization(bean: Any, beanName: String): Any? {
        if (beanFactory == null) {
            return bean
        }

        if (hasAnnotation(bean, Published::class)) {
            parentBeanFactory?.registerSingleton(beanName, bean)
            return bean
        }

        try {
            val beanDefinition = beanFactory.getBeanDefinition(beanName)
            if (beanDefinition.source is AnnotatedTypeMetadata) {
                val metadata = beanDefinition.source as AnnotatedTypeMetadata
                if (metadata.isAnnotated(Published::class.java.name)) {
                    parentBeanFactory?.registerSingleton(beanName, bean)
                }
            }
        } catch (ignore: NoSuchBeanDefinitionException) {
            // do nothing
        }

        return bean
    }

    private fun <A : Annotation> hasAnnotation(bean: Any, annotationType: KClass<A>): Boolean {
        var beanClass = ClassUtils.getUserClass(bean)
        if (AopUtils.isAopProxy(bean)) {
            beanClass = AopUtils.getTargetClass(bean)
        }

        return AnnotationUtils.findAnnotation(beanClass, annotationType.java) != null
    }
}
