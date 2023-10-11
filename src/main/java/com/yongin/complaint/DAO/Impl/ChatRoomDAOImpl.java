package com.yongin.complaint.DAO.Impl;

import com.yongin.complaint.DAO.ChatRoomDAO;
import com.yongin.complaint.DTO.ChatRoomInfoDTO;
import com.yongin.complaint.JPA.Entity.ChatRoomInfo;

import java.util.List;

public class ChatRoomDAOImpl implements ChatRoomDAO {
    @Override
    public boolean createChatRoom(ChatRoomInfoDTO chatRoomInfoDTO) {
        return false;
    }

    @Override
    public ChatRoomInfo getNewChatRoom() {
        return null;
    }

    @Override
    public List<ChatRoomInfo> getChatRoomInfoList() {
        return null;
    }
}
