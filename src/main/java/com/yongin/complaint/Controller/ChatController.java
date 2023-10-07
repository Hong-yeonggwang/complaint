package com.yongin.complaint.Controller;

import com.yongin.complaint.DTO.ChatRoomInfoDTO;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
public class ChatController {
    List<ChatRoomInfoDTO> roomList = new ArrayList<ChatRoomInfoDTO>();
    SecureRandom random  = new SecureRandom(); // 채팅방 고유 ID를 생성하기 위한 랜덤 객체

    /**
     * 방 생성하기
     * @param params
     * @return
     */
    @RequestMapping("/createChatRoom")
    @ResponseBody
    public List<ChatRoomInfoDTO> createRoom(@RequestParam String params){
        System.out.println("불럿냐?");

        System.out.println(params);
        JSONObject jsonObject = jsonToObjectParser(params);

        String roomName = (String) jsonObject.get("chatRoomName");
        if(roomName != null && !roomName.trim().equals("")) {
            System.out.println("이름있냐?");
            ChatRoomInfoDTO chatRoomInfoDTO = new ChatRoomInfoDTO();

            String uniqueId = new BigInteger(130, random).toString(32);

            chatRoomInfoDTO.setChatRoomId(uniqueId);
            chatRoomInfoDTO.setChatRoomName(roomName);
            chatRoomInfoDTO.setChatRoomCreatedDate(LocalDateTime.now());
            chatRoomInfoDTO.setChatRoomLimited(9); // 지금은 default지만 파라미터 받아서 설정할것임

            roomList.add(chatRoomInfoDTO);
        }

        System.out.println("다 만들었냐?");

        return roomList;
    }

    /**
     * 방 정보가져오기
     * @param params
     * @return
     */
    @RequestMapping("/getChatRoom")
    @ResponseBody
    public List<ChatRoomInfoDTO> getRoom(@RequestParam HashMap<Object, Object> params){
        return roomList;
    }

    private static JSONObject jsonToObjectParser(String jsonStr) {
        JSONParser parser = new JSONParser();
        JSONObject obj = null;
        try {
            obj = (JSONObject)parser.parse(jsonStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return obj;
    }
}
