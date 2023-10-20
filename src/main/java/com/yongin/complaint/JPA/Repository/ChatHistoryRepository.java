package com.yongin.complaint.JPA.Repository;

import com.yongin.complaint.JPA.Entity.ChatHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChatHistoryRepository extends JpaRepository<ChatHistory, Long> {

    @Query("SELECT ch FROM ChatHistory ch WHERE ch.chatRoomId = :CHATROOM_ID")
    List<ChatHistory> findChatHistoryByChatRoomId(@Param("CHATROOM_ID") String chatRoomId);
}
