package com.yongin.complaint.Controller;

import com.yongin.complaint.DTO.ChatRoomInfoDTO;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
public class ChatController {
    List<ChatRoomInfoDTO> roomList = new ArrayList<ChatRoomInfoDTO>();
    List<ChatRoomInfoDTO> myRoomList = new ArrayList<ChatRoomInfoDTO>();
    SecureRandom random  = new SecureRandom(); // 채팅방 고유 ID를 생성하기 위한 랜덤 객체

    /**
     * 방 생성하기
     * @param params
     * @return
     */
    @PostMapping(value = "/createChatRoom")
    public ChatRoomInfoDTO createRoom(@RequestBody JSONObject params){
        System.out.println("불럿냐?");
        ChatRoomInfoDTO newChatRoomInfoDTO = new ChatRoomInfoDTO();

        System.out.println(params);

        String roomName = (String) params.get("chatRoomName");
//        int maxUsers = (Integer) params.get("maxUsers");
//        String userId = (String) params.get("userId");

        boolean checkNewChatRoomId = false;

        if(roomName != null && !roomName.trim().equals("")) {
            String uniqueId = new BigInteger(130, random).toString(32);

            if(roomList.size() == 0) checkNewChatRoomId = true;

            while(!checkNewChatRoomId){
                for(int i = 0; i < roomList.size(); ++i){
                    // Id가 중복이면 새로 만들고 처음부터 다시 검사
                    if(uniqueId.equals(roomList.get(i).getChatRoomId())){
                        uniqueId = new BigInteger(130, random).toString(32);
                        i = 0;
                    }
                    if(i+1 == roomList.size()){
                        checkNewChatRoomId = true;
                    }
                }
            }

            newChatRoomInfoDTO.setChatRoomId(uniqueId);
            newChatRoomInfoDTO.setChatRoomName(roomName);
//            newChatRoomInfoDTO.setChatRoomName(userId); // 방 만든 사람
            newChatRoomInfoDTO.setChatRoomCreatedDate(LocalDateTime.now());
            newChatRoomInfoDTO.setChatRoomLimited(9); // 방생성 잘 되면 maxUsers 로 받을 예정

            roomList.add(newChatRoomInfoDTO);
        }

        return newChatRoomInfoDTO;
    }

    /**
     * 방 정보가져오기
     * @return
     */
    @PostMapping(value = "/getChatRoomList")
    public List<ChatRoomInfoDTO> getRoomList(){
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
