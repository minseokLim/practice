package com.minseoklim.toyproject2024.websocket.config

import com.minseoklim.toyproject2024.websocket.domain.service.TokenParser
import com.sun.security.auth.UserPrincipal
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpStatusCode
import org.springframework.http.server.ServerHttpRequest
import org.springframework.http.server.ServerHttpResponse
import org.springframework.http.server.ServletServerHttpRequest
import org.springframework.messaging.simp.config.MessageBrokerRegistry
import org.springframework.web.socket.WebSocketHandler
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker
import org.springframework.web.socket.config.annotation.StompEndpointRegistry
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer
import org.springframework.web.socket.server.HandshakeInterceptor
import org.springframework.web.socket.server.support.DefaultHandshakeHandler
import java.security.Principal

@Configuration
@EnableWebSocketMessageBroker
class WebSocketConfig(
    private val tokenParser: TokenParser
) : WebSocketMessageBrokerConfigurer {
    override fun configureMessageBroker(registry: MessageBrokerRegistry) {
        registry.enableSimpleBroker("/topic")
        registry.setApplicationDestinationPrefixes("/app")
    }

    override fun registerStompEndpoints(registry: StompEndpointRegistry) {
        registry.addEndpoint("/ws")
            .addInterceptors(
                object : HandshakeInterceptor {
                    override fun beforeHandshake(
                        request: ServerHttpRequest,
                        response: ServerHttpResponse,
                        wsHandler: WebSocketHandler,
                        attributes: MutableMap<String, Any>
                    ): Boolean {
                        val token: String? = (request as ServletServerHttpRequest).servletRequest.getParameter("token")
                        return if (token != null && tokenParser.isValidToken(token)) {
                            true
                        } else {
                            response.setStatusCode(HttpStatusCode.valueOf(401))
                            false
                        }
                    }

                    override fun afterHandshake(
                        request: ServerHttpRequest,
                        response: ServerHttpResponse,
                        wsHandler: WebSocketHandler,
                        exception: Exception?
                    ) {
                        // do nothing
                    }
                }
            )
            .setAllowedOriginPatterns("*")
            .setHandshakeHandler(
                object : DefaultHandshakeHandler() {
                    override fun determineUser(
                        request: ServerHttpRequest,
                        wsHandler: WebSocketHandler,
                        attributes: MutableMap<String, Any>
                    ): Principal? {
                        val token: String? = (request as ServletServerHttpRequest).servletRequest.getParameter("token")
                        return if (token != null) {
                            val memberId = tokenParser.extractMemberId(token)
                            UserPrincipal(memberId)
                        } else {
                            null
                        }
                    }
                }
            )
    }
}
