package chatroom.web;

import chatroom.model.ChatRoom;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Controller
@RequestMapping("/chat")
@RequiredArgsConstructor
public class ChatRoomController {
    private final AtomicInteger seq = new AtomicInteger(0);
    private RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/rooms")
    public String rooms(Model model){
        ResponseEntity<List<ChatRoom>> exchange = restTemplate.exchange("http://localhost:8080/rooms", HttpMethod.GET, null, new ParameterizedTypeReference<List<ChatRoom>>() {
        });

        model.addAttribute("rooms", exchange.getBody());
        return "/rooms";
    }

    @GetMapping("/rooms/{id}")
    public String room(@PathVariable String id, Model model){
        ResponseEntity<ChatRoom> forEntity = restTemplate.getForEntity("http://localhost:8080/rooms/" + id, ChatRoom.class);
        model.addAttribute("room", forEntity.getBody());
        model.addAttribute("member", "member" + seq.incrementAndGet());
        return "/room-detail";
    }
}
