package com.yongin.complaint.JPA.Repository;

import com.yongin.complaint.JPA.Entity.ChatRoomInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatRoomInfoRepository extends JpaRepository<ChatRoomInfo,Long> {

//    List<ChatRoomInfo> findAll();
}
