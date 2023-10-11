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
    List<ChatRoomInfo> roomList = new ArrayList<ChatRoomInfo>();
    List<ChatRoomInfoDTO> myRoomList = new ArrayList<ChatRoomInfoDTO>();
    final ChatService chatServiceImpl; // = ChatServiceImpl.getInstance();
    SecureRandom random  = new SecureRandom(); // 채팅방 고유 ID를 생성하기 위한 랜덤 객체

    @Autowired
    ChatController(ChatService chatServiceImpl){
        this.chatServiceImpl = chatServiceImpl;
    }

    /**
     * 방 생성하기
     * @param params
     * @return ChatRoomInfo : Entity
     */
    @PostMapping(value = "/createChatRoom")
    public ChatRoomInfo createRoom(@RequestBody JSONObject params) {
        ChatRoomInfoDTO newChatRoomInfoDTO = new ChatRoomInfoDTO(); // DB에 저장하기 전에 정보를 담아둘 객체
        ChatRoomInfo accomplishedChatRoomInfo = null;   // DB 저장 후 받을 객체

        System.out.println(params);

        String roomName = (String) params.get("chatRoomName");
        int maxUsers = (Integer) params.get("maxUsers");
//        String userId = (String) params.get("userId");

        boolean checkNewChatRoomId = false;

        if (roomName != null && !roomName.trim().equals("")) {
            String uniqueId = new BigInteger(130, random).toString(32);

            if (roomList.size() == 0) checkNewChatRoomId = true;

            while (!checkNewChatRoomId) {
                for (int i = 0; i < roomList.size(); ++i) {
                    // Id가 중복이면 새로 만들고 처음부터 다시 검사
                    if (uniqueId.equals(roomList.get(i).getChatRoomId())) {
                        uniqueId = new BigInteger(130, random).toString(32);
                        i = 0;
                    }
                    if (i + 1 == roomList.size()) {
                        checkNewChatRoomId = true;
                    }
                }
            }

            newChatRoomInfoDTO.setChatRoomId(uniqueId);
            newChatRoomInfoDTO.setChatRoomName(roomName);
//            newChatRoomInfoDTO.setChatRoomName(userId); // 방 만든 사람
            newChatRoomInfoDTO.setChatRoomCreatedDate(LocalDateTime.now());
            newChatRoomInfoDTO.setChatRoomLimited(maxUsers); // 방 인원 제한

            accomplishedChatRoomInfo = chatServiceImpl.CreateChatRoom(newChatRoomInfoDTO);

//            roomList.add(newChatRoomInfoDTO);
            roomList.add(accomplishedChatRoomInfo);
        }

//        return newChatRoomInfoDTO;
        return accomplishedChatRoomInfo;
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
     * @return
     */
//    @PostMapping(value = "/getMyChatRoomList")
//    public List<ChatRoomInfoDTO> getRoomList(String userId){
//        return myRoomList;
//    }
}
