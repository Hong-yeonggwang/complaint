package com.yongin.complaint.DAO;

import com.yongin.complaint.DTO.ChatRoomInfoDTO;

import java.util.List;

public interface ChatDAO {

    ChatRoomInfoDTO findChatRoomsWithMembers(String roomId);
}
