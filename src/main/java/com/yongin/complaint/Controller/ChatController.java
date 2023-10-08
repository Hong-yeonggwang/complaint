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
    SecureRandom random  = new SecureRandom(); // 채팅방 고유 ID를 생성하기 위한 랜덤 객체

    /**
     * 방 생성하기
     * @param params
     * @return
     */
    @PostMapping(value = "/createChatRoom")
    public boolean createRoom(@RequestBody JSONObject params){
        boolean createSuccess = false;
        System.out.println("불럿냐?");
        ChatRoomInfoDTO newChatRoomInfoDTO = new ChatRoomInfoDTO();

        System.out.println(params);

        String roomName = (String) params.get("chatRoomName");
//        int maxUsers = (Integer) params.get("maxUsers");
//        System.out.println(maxUsers);

        if(roomName != null && !roomName.trim().equals("")) {
            System.out.println("이름있냐?");

            String uniqueId = new BigInteger(130, random).toString(32);

            newChatRoomInfoDTO.setChatRoomId(uniqueId);
            newChatRoomInfoDTO.setChatRoomName(roomName);
            newChatRoomInfoDTO.setChatRoomCreatedDate(LocalDateTime.now());
            newChatRoomInfoDTO.setChatRoomLimited(9); // 방생성 잘 되면 maxUsers 로 받을 예정

            roomList.add(newChatRoomInfoDTO);
            createSuccess = true;
        }

        System.out.println("다 만들었냐? : " + roomList.size());

        return createSuccess;
    }

    /**
     * 방 정보가져오기
//     * @param params
     * @return
     */
    @PostMapping(value = "/getChatRoomList")
    public List<ChatRoomInfoDTO> getRoomList(){
//        @RequestBody HashMap<Object, Object> params
        return roomList;
    }

//    private static JSONObject jsonToObjectParser(String jsonStr) {
//        JSONParser parser = new JSONParser();
//        JSONObject obj = null;
//        try {
//            obj = (JSONObject)parser.parse(jsonStr);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        return obj;
//    }
}
