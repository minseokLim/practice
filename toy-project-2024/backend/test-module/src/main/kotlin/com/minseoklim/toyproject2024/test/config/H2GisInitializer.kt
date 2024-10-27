package com.minseoklim.toyproject2024.test.config

import org.h2gis.functions.factory.H2GISFunctions
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component
import javax.sql.DataSource

@Component
class H2GisInitializer(
    private val dataSource: DataSource
) {
    @EventListener(ContextRefreshedEvent::class)
    fun initSpatialFunctions() {
        dataSource.connection.use { connection ->
            H2GISFunctions.load(connection)
        }
    }
}
