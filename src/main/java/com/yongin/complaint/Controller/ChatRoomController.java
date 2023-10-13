package com.yongin.complaint.Controller;

import com.yongin.complaint.DTO.ChatRoomInfoDTO;
import com.yongin.complaint.JPA.Entity.ChatRoomInfo;
import com.yongin.complaint.JPA.Entity.Member;
import com.yongin.complaint.Service.Chat.ChatService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RestController
public class ChatRoomController {
//    List<ChatRoomInfoDTO> roomList = new ArrayList<ChatRoomInfoDTO>();
    final ChatService chatServiceImpl; // = ChatServiceImpl.getInstance();
    List<ChatRoomInfo> roomList = null;
    List<ChatRoomInfoDTO> myRoomList = null;
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    Member myInfo;

    @Autowired
    ChatRoomController(ChatService chatServiceImpl){
        this.chatServiceImpl = chatServiceImpl;
    }

    /**
     * 방 생성하기
     * @param jsonObjectParams
     * @return roomList : Entity List
     */
    @PostMapping(value = "/createChatRoom")
    public List<ChatRoomInfo> createRoom(@RequestBody JSONObject jsonObjectParams) {
//        myInfo = (Member)auth.getPrincipal();
        ChatRoomInfo newChatRoomInfo = new ChatRoomInfo();
//        ChatRoomInfoDTO newChatRoomInfoDTO = new ChatRoomInfoDTO(); // DB에 저장하기 전에 정보를 담아둘 객체

//        System.out.println(jsonObjectParams);

        String roomName = (String) jsonObjectParams.get("chatRoomName");
        int chatRoomLimited = (Integer) jsonObjectParams.get("chatRoomLimited");

        // 채팅방 이름과 인원 제한을 제대로 수신하면
        if (roomName != null && !roomName.trim().equals("") && chatRoomLimited > 1) {
            newChatRoomInfo.setChatRoomName(roomName);
            newChatRoomInfo.setChatRoomLimited(chatRoomLimited);
//            newChatRoomInfoDTO.setChatRoomName(roomName);
//            newChatRoomInfoDTO.setChatRoomLimited(maxUsers);

            roomList = chatServiceImpl.createChatRoom(newChatRoomInfo, myInfo);
        }
        else{ System.out.println("채팅방 이름 또는 인원 제한 파라미터가 제대로 수신되지 않았습니다."); }

        return roomList;
    }

    /**
     * 방 입장하기
     * @param chatRoomSeq
     * @return
     */
    @PostMapping(value = "/enterRoom")
    public void createRoom(@RequestBody Long chatRoomSeq) {
        
    }

    /**
     * 클라이언트가 직접 입력한 chat Url 검사
     * @param chatRoomId
     * @return
     */
//    @RequestMapping(value = "/checkChatRoomId/{chatRoomId}")
    public void checkChatRoomId(@PathVariable("chatRoomId") String chatRoomId){

    }

    /**
     * 방 정보가져오기
     * @return roomList : Entity List
     */
    @PostMapping(value = "/getChatRoomList")
    public List<ChatRoomInfo> getRoomList(){
        return roomList;
    }

    /**
     * 내 방 정보가져오기
     * @return myRoomList : Entity List
     */
//    @PostMapping(value = "/getMyChatRoomList")
//    public List<ChatRoomInfoDTO> getRoomList(String userId){
//        return myRoomList;
//    }
}
