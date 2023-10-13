package com.yongin.complaint.JPA.Repository;

import com.yongin.complaint.JPA.Entity.ChatRoomInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChatRoomInfoRepository extends JpaRepository<ChatRoomInfo,Long> {

//    List<ChatRoomInfo> findAll();
    boolean findByChatRoomId(String chatRoomId);
}
