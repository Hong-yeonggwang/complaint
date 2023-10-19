package com.yongin.complaint.Controller;

import com.yongin.complaint.DTO.ChatRoomInfoDTO;
import com.yongin.complaint.DTO.ChatRoomMemberDTO;
import com.yongin.complaint.JPA.Entity.ChatRoomInfo;
import com.yongin.complaint.JPA.Entity.Member;
import com.yongin.complaint.Payload.requset.CreateChatRoomRequest;
import com.yongin.complaint.Payload.requset.EnterChatRoomRequest;
import com.yongin.complaint.Payload.requset.ExitChatRoomRequest;
import com.yongin.complaint.Payload.response.ChatMyInfoResponse;
import com.yongin.complaint.Payload.response.EnterChatRoomResponse;
import com.yongin.complaint.Service.Chat.ChatRoomService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@RestController
public class ChatRoomController {
//    List<ChatRoomInfoDTO> roomList = new ArrayList<ChatRoomInfoDTO>();
    final ChatRoomService chatRoomServiceImpl; // = ChatServiceImpl.getInstance();
//    List<ChatRoomInfo> roomList = null;
    List<ChatRoomInfoDTO> chatRoomInfoDTOList;
    List<ChatRoomInfoDTO> myRoomList;

    @Autowired
    ChatRoomController(ChatRoomService chatRoomServiceImpl){
        this.chatRoomServiceImpl = chatRoomServiceImpl;
    }

    /**
     * 방 생성하기
     * @param createChatRoomRequest
     * @return roomList : Entity List
     */
    @PostMapping(value = "/createChatRoom")
    public List<ChatRoomInfoDTO> createChatRoom(@RequestBody CreateChatRoomRequest createChatRoomRequest) {
        // 토큰에 들어 있는 내 정보
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Member myInfo = (Member)auth.getPrincipal();

        ChatRoomInfo newChatRoomInfo = new ChatRoomInfo(); // 채팅방 생성시 DB에 저장할 Entity

        String roomName = createChatRoomRequest.getChatRoomName(); //(String) jsonObjectParams.get("chatRoomName");
        int chatRoomLimited = createChatRoomRequest.getChatRoomLimited(); //(Integer) jsonObjectParams.get("chatRoomLimited");


        // 채팅방 이름과 인원 제한을 제대로 수신하면
        if (roomName != null && !roomName.trim().equals("") && chatRoomLimited > 1) {
            newChatRoomInfo.setChatRoomName(roomName);
            newChatRoomInfo.setChatRoomLimited(chatRoomLimited);

            return chatRoomServiceImpl.createChatRoom(newChatRoomInfo, myInfo);
        }
        else{
            System.out.println("채팅방 이름 또는 인원 제한 파라미터가 제대로 수신되지 않았습니다.");

            return null;
        }
    }

    /**
     * 방 정보가져오기
     * @return roomList : Entity List
     */
    @PostMapping(value = "/getChatRoomList")
    public List<ChatRoomInfoDTO> getRoomList(){
        return chatRoomServiceImpl.getChatRoomInfoDTOList();
    }

    /**
     * 방 입장하기
     * @param enterChatRoomRequest
     * @return EnterChatRoomResponse
     */
    @PostMapping(value = "/enterChatRoom")
    public EnterChatRoomResponse enterChatRoom(@RequestBody EnterChatRoomRequest enterChatRoomRequest) {
        // 토큰에 들어 있는 내 정보
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Member myInfo = (Member)auth.getPrincipal();

        String chatRoomId = enterChatRoomRequest.getChatRoomId();
        System.out.println("enterChatRoom: "+ chatRoomId);

        return chatRoomServiceImpl.enterChatRoom(chatRoomId, myInfo);
    }

    /**
     * 채팅방 내 정보 가져오기
     * @return ChatRoomMemberDTO
     */
//    @PostMapping(value = "/getChatMyInfo")
//    public ChatMyInfoResponse getChatMyInfo() {
//        // 토큰에 들어 있는 내 정보
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        Member myInfo = (Member)auth.getPrincipal();
//
//        return new ChatMyInfoResponse(myInfo.getMemberSeq(), myInfo.getNickName());
//    }

    /**
     * 방 퇴장하기
     * @return void
     */
    @PostMapping(value = "/exitChatRoom")
    public void exitChatRoom(@RequestBody ExitChatRoomRequest exitChatRoomRequest) {
        System.out.println(exitChatRoomRequest);

        // 1명 밖에 없으면 바로 폭파
        if(exitChatRoomRequest.getCurrentNumberOfPeople() == 1){
            chatRoomServiceImpl.deleteChatRoom(exitChatRoomRequest.getChatRoomSeq());
        }
        else{
            // 토큰에 들어 있는 내 정보
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            Member myInfo = (Member)auth.getPrincipal();

            chatRoomServiceImpl.exitChatRoom(exitChatRoomRequest.getChatRoomSeq(), exitChatRoomRequest.getCurrentNumberOfPeople(), myInfo);
        }
    }



//    /**
//     * 클라이언트가 직접 입력한 chat Url 검사
//     * @param chatRoomId
//     * @return
//     */
////    @RequestMapping(value = "/checkChatRoomId/{chatRoomId}")
//    public void checkChatRoomId(@PathVariable("chatRoomId") String chatRoomId){
//
//    }



    /**
     * 내 방 정보가져오기
     * @return myRoomList : Entity List
     */
//    @PostMapping(value = "/getMyChatRoomList")
//    public List<ChatRoomInfoDTO> getRoomList(String userId){
//        return myRoomList;
//    }
}
