package com.yongin.complaint.Service.Chat.ChatImpl;

import com.yongin.complaint.DTO.ChatRoomInfoDTO;
import com.yongin.complaint.Payload.response.ChatRoomInfoResponse;
import com.yongin.complaint.Payload.response.CreateChatRoomResponse;
import com.yongin.complaint.Service.Chat.ChatService;

public class ChatServiceImpl implements ChatService {
    @Override
    public CreateChatRoomResponse CreateChatRoom(ChatRoomInfoDTO chatRoomInfoDTO) {
        return null;
    }

    @Override
    public boolean dropChatRoom(ChatRoomInfoDTO chatRoomInfoDTO) {
        boolean result = false;

        return result;
    }

    @Override
    public ChatRoomInfoResponse getChatRoomList() {

        return null;
    }

    @Override
    public ChatRoomInfoResponse getMyChatRoomList(String userId) {

        return null;
    }
}
