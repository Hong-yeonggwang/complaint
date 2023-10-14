package com.yongin.complaint.JPA.Repository;

import com.yongin.complaint.DTO.ChatRoomInfoDTO;
import com.yongin.complaint.JPA.Entity.ChatRoomInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChatRoomInfoRepository extends JpaRepository<ChatRoomInfo,Long> {

//    @Query("SELECT crif FROM ChatRoomInfo crif")
    List<ChatRoomInfo> findAll();
    boolean findByChatRoomId(String chatRoomId);

//    @Query("SELECT new com.yongin.complaint.DTO.ChatRoomInfoDTO(c.chatRoomSeq, c.chatRoomId, c.chatRoomName, c.currentNumberOfPeople, c.chatRoomLimited, c.chatRoomCreatedDate)," +
//            "new com.yongin.complaint.DTO.ChatRoomMemberDTO(m.memberSeq, m.nickName)) FROM ChatRoomInfo c INNER JOIN c.members m")
//    List<ChatRoomInfoDTO> findChatRoomsWithMembers();

}
