package com.yongin.complaint.DAO;

import com.yongin.complaint.DTO.ChatRoomInfoDTO;
import com.yongin.complaint.JPA.Entity.ChatRoomInfo;

import java.util.List;

public interface ChatRoomDAO {

    boolean createChatRoom(ChatRoomInfoDTO chatRoomInfoDTO);
    ChatRoomInfo getNewChatRoom();
    List<ChatRoomInfo> getChatRoomInfoList();
}
