package com.minseoklim.toyproject2024.payment.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties("port-one")
class PortOneProperty {
    lateinit var baseUrl: String
    lateinit var key: String
    lateinit var secret: String
    lateinit var pg: String
}
