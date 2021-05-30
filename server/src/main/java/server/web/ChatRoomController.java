package server.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import server.model.ChatRoom;
import server.repository.ChatRoomRepository;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ChatRoomController {
    private final ChatRoomRepository repository;

    @GetMapping("/rooms")
    public List<ChatRoom> rooms(){
        return repository.getChatRooms();
    }

    @GetMapping("/rooms/{id}")
    public ChatRoom room(@PathVariable String id){
        return repository.getChatRoom(id);
    }
}
