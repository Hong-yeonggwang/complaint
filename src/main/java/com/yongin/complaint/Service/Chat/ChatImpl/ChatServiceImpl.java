package com.yongin.complaint.Service.Chat.ChatImpl;

import com.yongin.complaint.DTO.ChatRoomInfoDTO;
import com.yongin.complaint.JPA.Entity.ChatRoomInfo;
import com.yongin.complaint.Payload.response.ChatRoomInfoResponse;
import com.yongin.complaint.Service.Chat.ChatService;
import org.springframework.stereotype.Service;

@Service
public final class ChatServiceImpl implements ChatService {

//    public static final ChatService chatServiceImpl = new ChatServiceImpl();
//
//    public static final ChatService getInstance() {
//        return chatServiceImpl;
//    }

    @Override
    public ChatRoomInfo CreateChatRoom(ChatRoomInfoDTO chatRoomInfoDTO) {
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
