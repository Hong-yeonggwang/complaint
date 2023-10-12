package com.yongin.complaint.Service.Chat;

import com.yongin.complaint.DTO.ChatRoomInfoDTO;
import com.yongin.complaint.JPA.Entity.ChatRoomInfo;
import com.yongin.complaint.Payload.response.ChatRoomInfoResponse;

import java.util.List;

public interface ChatService {

    List<ChatRoomInfo> CreateChatRoom(ChatRoomInfoDTO newChatRoomInfoDTO);

    boolean dropChatRoom(ChatRoomInfoDTO chatRoomInfoDTO);

    List<ChatRoomInfo> getChatRoomList();

    List<ChatRoomInfo> getMyChatRoomList(String userId);
}
