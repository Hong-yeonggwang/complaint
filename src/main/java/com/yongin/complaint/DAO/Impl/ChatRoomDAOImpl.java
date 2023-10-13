package com.yongin.complaint.DAO.Impl;

import com.yongin.complaint.DAO.ChatRoomDAO;
import com.yongin.complaint.DTO.ChatRoomInfoDTO;
import com.yongin.complaint.JPA.Entity.ChatRoomInfo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ChatRoomDAOImpl implements ChatRoomDAO {
    List<ChatRoomInfo> roomList = null; // 리스트 반환용

    @Override
    public boolean createChatRoom(ChatRoomInfoDTO newChatRoomInfoDTO) {
        boolean result = false;

        return result;
    }

    @Override
    public List<ChatRoomInfo> getChatRoomInfoList() {
        return roomList;
    }

    @Override
    public ChatRoomInfo getLastestChatRoomInfo() {
        ChatRoomInfo LastestChatRoomInfo = null;

        return LastestChatRoomInfo;
    }


}
