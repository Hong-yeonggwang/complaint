package com.yongin.complaint.DAO;

import com.yongin.complaint.DTO.ChatRoomInfoDTO;

public interface ChatRoomDAO {

    ChatRoomInfoDTO getChatRoomInfoDTOWithMembers(String roomId);
}
