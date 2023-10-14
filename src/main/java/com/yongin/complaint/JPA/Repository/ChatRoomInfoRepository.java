package com.yongin.complaint.JPA.Repository;

import com.yongin.complaint.DTO.ChatRoomInfoDTO;
import com.yongin.complaint.DTO.ChatRoomMemberDTO;
import com.yongin.complaint.JPA.Entity.ChatRoomInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChatRoomInfoRepository extends JpaRepository<ChatRoomInfo,Long> {

//    @Query("SELECT crif FROM ChatRoomInfo crif")
    List<ChatRoomInfo> findAll();
    boolean findByChatRoomId(String chatRoomId);

//    @Query("SELECT new com.yongin.complaint.DTO.ChatRoomInfoDTO(c.chatRoomSeq, c.chatRoomId, c.chatRoomName, c.currentNumberOfPeople, c.chatRoomLimited, c.chatRoomCreatedDate," +
//            "new com.yongin.complaint.DTO.ChatRoomMemberDTO(m.memberSeq, m.nickName)) FROM ChatRoomInfo c INNER JOIN c.members m")
//    List<ChatRoomInfoDTO> findChatRoomsWithMembers();

    @Query("SELECT new com.yongin.complaint.DTO.ChatRoomInfoDTO(c.chatRoomSeq, c.chatRoomId, c.chatRoomName, c.currentNumberOfPeople, c.chatRoomLimited"+
            ") FROM ChatRoomInfo c WHERE c.chatRoomId = :CHATROOMID")
//    @Query("SELECT new com.yongin.complaint.DTO.ChatRoomInfoDTO(c.chatRoomSeq, c.chatRoomId, c.chatRoomName, c.currentNumberOfPeople, c.chatRoomLimited, c.chatRoomCreatedDate," +
//            "(select new com.yongin.complaint.DTO.ChatRoomMemberDTO(m.memberSeq, m.nickName) from c.members m)) FROM ChatRoomInfo c  WHERE c.chatRoomId = :CHATROOMID")
    ChatRoomInfoDTO getRoomInfo(@Param("CHATROOMID") String chatRoomId);

    @Query("SELECT new com.yongin.complaint.DTO.ChatRoomMemberDTO(m.memberSeq,m.nickName) FROM ChatRoomInfo c join c.members m WHERE c.chatRoomId = :CHATROOMID")
    List<ChatRoomMemberDTO> getRoomUsers(@Param("CHATROOMID") String chatRoomId);

}
