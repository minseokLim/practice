package com.minseoklim.modularmonoliths

import com.minseoklim.modularmonoliths.catalogs.CatalogContextConfiguration
import com.minseoklim.modularmonoliths.orders.OrderContextConfiguration
import com.minseoklim.modularmonoliths.shipments.ShipmentContextConfiguration
import org.springframework.boot.SpringBootConfiguration
import org.springframework.boot.WebApplicationType
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.context.annotation.Configuration

@SpringBootConfiguration
@EnableAutoConfiguration
class ModularMonolithsApplication

private const val PROPS_CONFIG_NAME = "spring.config.name: application, catalogs, orders, shipments"

@Configuration
@EnableAutoConfiguration
class WebContextConfiguration

fun main(args: Array<String>) {
    val application = SpringApplicationBuilder()
        .properties(PROPS_CONFIG_NAME)
        .sources(ModularMonolithsApplication::class.java).web(WebApplicationType.NONE)
        .child(CatalogContextConfiguration::class.java).web(WebApplicationType.NONE)
        .sibling(OrderContextConfiguration::class.java).web(WebApplicationType.NONE)
        .sibling(ShipmentContextConfiguration::class.java).web(WebApplicationType.NONE)
        .sibling(WebContextConfiguration::class.java).web(WebApplicationType.SERVLET)
        .build()

    application.run(*args)
}
