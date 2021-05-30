package server.repository;

import org.springframework.stereotype.Repository;
import server.model.ChatRoom;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
public class ChatRoomRepository {
    private final Map<String, ChatRoom> chatRoomMap;
    private final List<ChatRoom> chatRoomList;

    public ChatRoomRepository(){
        chatRoomMap = Collections.unmodifiableMap(
                Stream.of(ChatRoom.create("1번방", "1ID"),ChatRoom.create("2번방", "2ID"),ChatRoom.create("3번방", "3ID"))
                .collect(Collectors.toMap(ChatRoom::getId, Function.identity()))
        );
        chatRoomList = Collections.unmodifiableList(new ArrayList<>(chatRoomMap.values()));
    }

    public ChatRoom getChatRoom(String id){
        return chatRoomMap.get(id);
    }

    public List<ChatRoom> getChatRooms(){
        return chatRoomList;
    }
}
