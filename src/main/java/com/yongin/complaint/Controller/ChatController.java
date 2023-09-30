package com.yongin.complaint.Controller;

import com.yongin.complaint.Payload.requset.SendingMessageRequest;
import com.yongin.complaint.Payload.response.SendingMessageResponse;
import com.yongin.complaint.Service.Chat.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chat/{roomId}")
public class ChatController {

    private final ChatService chatService;

    @Autowired
    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }


    @PostMapping("/chat/{roomId}")
    public ResponseEntity<SendingMessageResponse> sendMessage(@RequestBody SendingMessageRequest request) {
        SendingMessageResponse response = chatService.sendMessage(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
