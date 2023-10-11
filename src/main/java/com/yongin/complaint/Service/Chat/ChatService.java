package com.yongin.complaint.Service.Chat;

import com.yongin.complaint.DTO.ChatRoomInfoDTO;
import com.yongin.complaint.JPA.Entity.ChatRoomInfo;
import com.yongin.complaint.Payload.response.ChatRoomInfoResponse;
import com.yongin.complaint.Payload.response.CreateChatRoomResponse;

public interface ChatService {

    ChatRoomInfo CreateChatRoom(ChatRoomInfoDTO chatRoomInfoDTO);

    boolean dropChatRoom(ChatRoomInfoDTO chatRoomInfoDTO);

    ChatRoomInfoResponse getChatRoomList();

    ChatRoomInfoResponse getMyChatRoomList(String userId);
}
