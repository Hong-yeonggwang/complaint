package com.yongin.complaint.Service.Chat.ChatImpl;

import com.yongin.complaint.JPA.Entity.ChatHistory;
import com.yongin.complaint.JPA.Repository.ChatHistoryRepository;
import com.yongin.complaint.Service.Chat.ChatHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatHistoryServiceImpl implements ChatHistoryService {

    @Autowired
    ChatHistoryRepository chatHistoryRepository;

    @Override
    public List<ChatHistory> getChatHistories(String chatRoomId) {
        return chatHistoryRepository.findChatHistoryByChatRoomId(chatRoomId);
    }
}
