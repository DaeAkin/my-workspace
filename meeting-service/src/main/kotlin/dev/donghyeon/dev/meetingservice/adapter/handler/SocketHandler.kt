package dev.donghyeon.dev.meetingservice.adapter.handler

import org.springframework.stereotype.Component
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler
import java.util.concurrent.CopyOnWriteArrayList

@Component
class SocketHandler: TextWebSocketHandler() {
    
    val sessions: CopyOnWriteArrayList<WebSocketSession> = CopyOnWriteArrayList()
    
    override fun handleTextMessage(session: WebSocketSession, message: TextMessage) {
        sessions.forEach{
            if(it.isOpen && !session.id.equals(it.id))
                it.sendMessage(message)
        }
    }

    override fun afterConnectionEstablished(session: WebSocketSession) {
        sessions.add(session)
    }
}
