package com.yongin.complaint.Service.Chat;

import com.yongin.complaint.DTO.ChatRoomInfoDTO;
import com.yongin.complaint.DTO.ChatRoomMemberDTO;
import com.yongin.complaint.JPA.Entity.ChatRoomInfo;
import com.yongin.complaint.JPA.Entity.Member;

import java.util.List;

public interface ChatRoomService {

    List<ChatRoomInfoDTO> getChatRoomInfoDTOList();

    List<ChatRoomInfoDTO> createChatRoom(ChatRoomInfo newChatRoomInfo, Member myInfo);

    ChatRoomInfoDTO enterChatRoom(ChatRoomInfoDTO chatRoomInfoDTO, Member myInfo);

//    boolean checkChatRoomId(String chatRoomId);

//    ChatRoomInfoDTO getChatRoomInfoDTObySeq(String chatRoomId);

//    void enterChatRoom(Long chatRoomSeq);

    boolean dropChatRoom(ChatRoomInfoDTO chatRoomInfoDTO);

    List<ChatRoomInfo> getMyChatRoomList(String userId);

}