package com.yongin.complaint.Service.Chat;

import com.yongin.complaint.JPA.Entity.ChatHistory;

import java.util.List;

public interface ChatHistoryService {
    List<ChatHistory> getChatHistories(String chatRoomId);
}
