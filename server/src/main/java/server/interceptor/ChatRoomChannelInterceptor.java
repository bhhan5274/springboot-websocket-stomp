package server.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;

@Slf4j
public class ChatRoomChannelInterceptor implements ChannelInterceptor {
    @Override
    public void postSend(Message<?> message, MessageChannel channel, boolean sent) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
        String sessionId = accessor.getSessionId();

        switch (accessor.getCommand()){
            case CONNECT:
                log.info("Enter Room: sessionId=[{}]", sessionId);
                break;
            case DISCONNECT:
                log.info("Leave Room: sessionId=[{}]", sessionId);
                break;
            default:
                break;
        }
    }
}
