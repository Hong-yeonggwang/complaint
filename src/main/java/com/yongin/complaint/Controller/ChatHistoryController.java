package com.yongin.complaint.Controller;

import com.yongin.complaint.JPA.Entity.ChatHistory;
import com.yongin.complaint.Payload.requset.ChatRoomIdRequest;
import com.yongin.complaint.Service.Chat.ChatHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ChatHistoryController {

    @Autowired
    ChatHistoryService chatHistoryServiceImpl;

    @PostMapping(value = "/getChatHistories")
    List<ChatHistory> getChatHistoires(@RequestBody ChatRoomIdRequest chatRoomIdRequest){
        return chatHistoryServiceImpl.getChatHistories(chatRoomIdRequest.getChatRoomId());
    }
}
