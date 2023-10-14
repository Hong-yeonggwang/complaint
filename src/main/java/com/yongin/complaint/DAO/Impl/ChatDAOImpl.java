package com.yongin.complaint.DAO.Impl;

import com.yongin.complaint.DAO.ChatDAO;
import com.yongin.complaint.DTO.ChatRoomInfoDTO;
import com.yongin.complaint.DTO.ChatRoomMemberDTO;
import com.yongin.complaint.JPA.Repository.ChatRoomInfoRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class ChatDAOImpl implements  ChatDAO{
    private final ChatRoomInfoRepository chatRoomInfoRepository;

    public ChatDAOImpl(ChatRoomInfoRepository chatRoomInfoRepository){
        this.chatRoomInfoRepository = chatRoomInfoRepository;
    }

    @Override
    @Transactional
    public ChatRoomInfoDTO findChatRoomsWithMembers(String roomId) {
        ChatRoomInfoDTO roomInfo =  chatRoomInfoRepository.getRoomInfo(roomId);
        List<ChatRoomMemberDTO> memberList = chatRoomInfoRepository.getRoomUsers(roomId);

        roomInfo.setMembers(memberList);
        return roomInfo;
    }
}
