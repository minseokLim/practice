package com.minseoklim.modularmonoliths.catalogs

import com.minseoklim.modularmonoliths.commons.context.beans.PublishedComponentRegisteringPostProcessor
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory
import org.springframework.stereotype.Component

@Component
class CatalogPublishedComponentRegisteringPostProcessor(
    beanFactory: ConfigurableListableBeanFactory?
) : PublishedComponentRegisteringPostProcessor(beanFactory)
