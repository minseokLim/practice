package com.minseoklim.modularmonoliths.orders

import com.minseoklim.modularmonoliths.commons.context.beans.PublishedComponentRegisteringPostProcessor
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory
import org.springframework.stereotype.Component

@Component
class OrderPublishedComponentRegisteringPostProcessor(
    beanFactory: ConfigurableListableBeanFactory?
) : PublishedComponentRegisteringPostProcessor(beanFactory)
