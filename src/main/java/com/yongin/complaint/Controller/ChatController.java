package com.yongin.complaint.Controller;

import com.yongin.complaint.DTO.ChatRoomInfoDTO;
import com.yongin.complaint.JPA.Entity.ChatRoomInfo;
import com.yongin.complaint.Service.Chat.ChatImpl.ChatServiceImpl;
import com.yongin.complaint.Service.Chat.ChatService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ChatController {
//    List<ChatRoomInfoDTO> roomList = new ArrayList<ChatRoomInfoDTO>();
    List<ChatRoomInfo> roomList = null;
    List<ChatRoomInfoDTO> myRoomList = null;
    final ChatService chatServiceImpl; // = ChatServiceImpl.getInstance();

    @Autowired
    ChatController(ChatService chatServiceImpl){
        this.chatServiceImpl = chatServiceImpl;
    }

    /**
     * 방 생성하기
     * @param jsonObjectParams
     * @return ChatRoomInfo : Entity
     */
    @PostMapping(value = "/createChatRoom")
    public List<ChatRoomInfo> createRoom(@RequestBody JSONObject jsonObjectParams) {
        ChatRoomInfoDTO newChatRoomInfoDTO = new ChatRoomInfoDTO(); // DB에 저장하기 전에 정보를 담아둘 객체

//        System.out.println(jsonObjectParams);

        String roomName = (String) jsonObjectParams.get("chatRoomName");
        int maxUsers = (Integer) jsonObjectParams.get("maxUsers");

        // 채팅방 이름과 인원 제한을 제대로 수신하면
        if (roomName != null && !roomName.trim().equals("") && maxUsers > 1) {
            newChatRoomInfoDTO.setChatRoomName(roomName);
            newChatRoomInfoDTO.setChatRoomLimited(maxUsers);

            roomList = chatServiceImpl.CreateChatRoom(newChatRoomInfoDTO);
        }
        else{ System.out.println("채팅방 이름 또는 인원 제한 파라미터가 제대로 수신되지 않았습니다."); }

        return roomList;
    }

    /**
     * 방 정보가져오기
     * @return List<ChatRoomInfoDTO> : DTO List
     */
//    @PostMapping(value = "/getChatRoomList")
//    public List<ChatRoomInfoDTO> getRoomList(){
//        return roomList;
//    }


    /**
     * 방 정보가져오기
     * @return List<ChatRoomInfo> : Entity List
     */
    @PostMapping(value = "/getChatRoomList")
    public List<ChatRoomInfo> getRoomList(){
        return roomList;
    }

    /**
     * 내 방 정보가져오기
     * @return List<ChatRoomInfo> : Entity List
     */
//    @PostMapping(value = "/getMyChatRoomList")
//    public List<ChatRoomInfoDTO> getRoomList(String userId){
//        return myRoomList;
//    }
}
