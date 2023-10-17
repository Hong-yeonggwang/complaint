package com.yongin.complaint.DAO;

import com.yongin.complaint.DTO.ChatRoomInfoDTO;
import com.yongin.complaint.JPA.Entity.ChatRoomInfo;

import java.util.List;

public interface ChatRoomDAO {

//    ChatRoomInfoDTO createChatRoom(ChatRoomInfoDTO newChatRoomInfoDTO);
    ChatRoomInfoDTO findChatRoomsWithMembers(String roomId);

    //    ChatRoomInfo getLastestChatRoomInfo();
    //    List<ChatRoomInfo> getChatRoomInfoList();

}
