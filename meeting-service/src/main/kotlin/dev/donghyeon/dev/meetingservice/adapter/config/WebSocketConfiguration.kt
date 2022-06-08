package dev.donghyeon.dev.meetingservice.adapter.config

import dev.donghyeon.dev.meetingservice.adapter.handler.SocketHandler
import org.springframework.context.annotation.Configuration
import org.springframework.web.socket.config.annotation.EnableWebSocket
import org.springframework.web.socket.config.annotation.WebSocketConfigurer
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry



@Configuration
@EnableWebSocket
class WebSocketConfiguration: WebSocketConfigurer {
    override fun registerWebSocketHandlers(registry: WebSocketHandlerRegistry) {
        registry.addHandler(SocketHandler(), "/socket")
            .setAllowedOrigins("*")
    }
}
