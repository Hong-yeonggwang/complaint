package com.yongin.complaint.Service.Chat;

import com.yongin.complaint.Payload.requset.SendingMessageRequest;
import com.yongin.complaint.Payload.response.SendingMessageResponse;

public interface ChatService {
    SendingMessageResponse sendMessage(SendingMessageRequest request);

    // 생성되어 있는 웹소켓에 요청받은 정보 보내기
    // 소켓 통신이 되
}