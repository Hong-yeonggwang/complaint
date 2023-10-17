package com.yongin.complaint.Service.Chat;

import com.yongin.complaint.DTO.ChatRoomInfoDTO;
import com.yongin.complaint.DTO.ChatRoomMemberDTO;
import com.yongin.complaint.JPA.Entity.ChatRoomInfo;
import com.yongin.complaint.JPA.Entity.Member;
import com.yongin.complaint.Payload.requset.ExitChatRoomRequest;

import java.util.List;

public interface ChatRoomService {

    List<ChatRoomInfoDTO> getChatRoomInfoDTOList();

    List<ChatRoomInfoDTO> createChatRoom(ChatRoomInfo newChatRoomInfo, Member myInfo);

    ChatRoomInfoDTO getChatRoomInfoDTOWithMembers(String chatRoomId);
    ChatRoomInfoDTO enterChatRoom(String chatRoomId, Member myInfo);
    ChatRoomInfoDTO exitChatRoom(ExitChatRoomRequest exitChatRoomRequest, Member myInfo);

//    boolean checkChatRoomId(String chatRoomId);

//    ChatRoomInfoDTO getChatRoomInfoDTObySeq(String chatRoomId);

//    void enterChatRoom(Long chatRoomSeq);

    boolean dropChatRoom(ChatRoomInfoDTO chatRoomInfoDTO);

    List<ChatRoomInfo> getMyChatRoomList(String userId);

}