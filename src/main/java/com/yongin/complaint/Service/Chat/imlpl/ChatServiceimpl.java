package com.yongin.complaint.Service.Chat.imlpl;


import com.yongin.complaint.Payload.requset.SendingMessageRequest;
import com.yongin.complaint.Payload.response.SendingMessageResponse;
import com.yongin.complaint.Service.Chat.ChatService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ChatServiceimpl implements ChatService {

    @Override
    public SendingMessageResponse sendMessage(SendingMessageRequest request) {
        // 채팅 메시지를 처리하고 응답 생성
        SendingMessageResponse response = new SendingMessageResponse();
        response.setSender(request.getSender());
        response.setMessage(request.getMessage());
        response.setTimestamp(LocalDateTime.now());
        return response;
    }
}
