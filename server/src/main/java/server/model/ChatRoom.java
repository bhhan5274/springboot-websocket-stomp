package server.model;

import lombok.Getter;

import java.util.UUID;

@Getter
public class ChatRoom {
    private String id;
    private String name;

    public static ChatRoom create(String name, String id){
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.id = id;
        chatRoom.name = name;
        return chatRoom;
    }
}
