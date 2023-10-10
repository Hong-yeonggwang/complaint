package com.yongin.complaint.JPA.Repository;

import com.yongin.complaint.JPA.Entity.ChatRoomInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomInfoRepository extends JpaRepository<ChatRoomInfo,Long> {
}
