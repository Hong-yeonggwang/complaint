package com.yongin.complaint.JPA.Repository;

import com.yongin.complaint.JPA.Entity.ChatHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatHistoryRepository extends JpaRepository<ChatHistory, Long> {
}
