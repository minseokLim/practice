package com.minseoklim.modularmonoliths.shipments

import com.minseoklim.modularmonoliths.commons.context.beans.PublishedComponentRegisteringPostProcessor
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory
import org.springframework.stereotype.Component

@Component
class ShipmentPublishedComponentRegisteringPostProcessor(
    beanFactory: ConfigurableListableBeanFactory?
) : PublishedComponentRegisteringPostProcessor(beanFactory)
