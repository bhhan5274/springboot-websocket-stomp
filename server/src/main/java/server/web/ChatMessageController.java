package server.web;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;
import server.model.ChatMessage;

@RestController
@RequiredArgsConstructor
public class ChatMessageController {
    private final SimpMessagingTemplate template;

    @MessageMapping("/chat/join")
    public void join(ChatMessage message){
        message.setMessage(message.getWriter() + "님이 입장하였습니다.");
        template.convertAndSend("/subscribe/chat/room/" + message.getChatRoomId(), message);
    }

    @MessageMapping("/chat/message")
    public void message(ChatMessage message){
        template.convertAndSend("/subscribe/chat/room/" + message.getChatRoomId(), message);
    }
}
