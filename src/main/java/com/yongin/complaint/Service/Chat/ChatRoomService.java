package com.yongin.complaint.Service.Chat;

import com.yongin.complaint.DTO.ChatRoomInfoDTO;
import com.yongin.complaint.DTO.ChatRoomMemberDTO;
import com.yongin.complaint.JPA.Entity.ChatRoomInfo;
import com.yongin.complaint.JPA.Entity.Member;
import com.yongin.complaint.Payload.requset.ExitChatRoomRequest;
import com.yongin.complaint.Payload.response.EnterChatRoomResponse;

import java.util.List;

public interface ChatRoomService {

    List<ChatRoomInfoDTO> getChatRoomInfoDTOList();

    List<ChatRoomInfoDTO> createChatRoom(ChatRoomInfo newChatRoomInfo, Member myInfo);

    EnterChatRoomResponse enterChatRoom(String chatRoomId, Member myInfo);
    void exitChatRoom(Long chatRoomSeq, int currentNumberOfPeople, Member myInfo);

    void deleteChatRoom(Long chatRoomSeq);

    ChatRoomInfoDTO refreshChatRoomInfo(String chatRoomId);

    List<ChatRoomInfoDTO> getMyChatRoomInfoDTOList(Member myInfo);
}