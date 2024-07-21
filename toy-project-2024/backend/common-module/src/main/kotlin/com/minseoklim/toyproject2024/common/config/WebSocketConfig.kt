package com.minseoklim.toyproject2024.common.config

import com.sun.security.auth.UserPrincipal
import org.springframework.context.annotation.Configuration
import org.springframework.http.server.ServerHttpRequest
import org.springframework.messaging.simp.config.MessageBrokerRegistry
import org.springframework.web.socket.WebSocketHandler
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker
import org.springframework.web.socket.config.annotation.StompEndpointRegistry
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer
import org.springframework.web.socket.server.support.DefaultHandshakeHandler
import java.security.Principal

@Configuration
@EnableWebSocketMessageBroker
class WebSocketConfig : WebSocketMessageBrokerConfigurer {
    override fun configureMessageBroker(registry: MessageBrokerRegistry) {
        registry.enableSimpleBroker("/topic")
        registry.setApplicationDestinationPrefixes("/app")
    }

    override fun registerStompEndpoints(registry: StompEndpointRegistry) {
        registry.addEndpoint("/ws")
            .setAllowedOriginPatterns("*")
            .setHandshakeHandler(
                object : DefaultHandshakeHandler() {
                    override fun determineUser(
                        request: ServerHttpRequest,
                        wsHandler: WebSocketHandler,
                        attributes: MutableMap<String, Any>
                    ): Principal? {
                        return request.getMemberId()?.let { UserPrincipal(it) }
                    }
                }
            )
    }

    private fun ServerHttpRequest.getMemberId(): String? {
        return uri.query?.let {
            extractKeyValueFromQueryParameters(it)["memberId"]
        }
    }

    private fun extractKeyValueFromQueryParameters(query: String): Map<String, String> {
        return query.split("&")
            .map { it.split("=") }
            .associate {
                if (it.size == 1) {
                    it[0] to ""
                } else {
                    it[0] to it[1]
                }
            }
    }
}
