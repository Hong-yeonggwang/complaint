package com.yongin.complaint.DAO.Impl;

import com.yongin.complaint.DAO.ChatRoomDAO;
import com.yongin.complaint.DTO.ChatRoomInfoDTO;
import com.yongin.complaint.DTO.ChatRoomMemberDTO;
import com.yongin.complaint.JPA.Entity.ChatRoomInfo;
import com.yongin.complaint.JPA.Repository.ChatRoomInfoRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class ChatRoomDAOImpl implements ChatRoomDAO {
//    List<ChatRoomInfo> roomList = null; // 리스트 반환용
    ChatRoomInfoDTO newChatRoomInfoDTO;

    private final ChatRoomInfoRepository chatRoomInfoRepository;

    public ChatRoomDAOImpl(ChatRoomInfoRepository chatRoomInfoRepository){
        this.chatRoomInfoRepository = chatRoomInfoRepository;
    }

//    @Override
//    public boolean createChatRoom(ChatRoomInfoDTO newChatRoomInfoDTO) {
//        boolean result = false;
//
//        return result;
//    }

    @Override
    @Transactional
    public ChatRoomInfoDTO getChatRoomInfoDTOWithMembers(String chatRoomId) {
        ChatRoomInfoDTO chatRoomInfoDTO =  chatRoomInfoRepository.getChatRoomInfoDTOByChatRoomId(chatRoomId);
//        System.out.println("getChatRoomInfoDTOWithMembers: " + chatRoomInfoDTO);

        if(chatRoomInfoDTO != null){
            List<ChatRoomMemberDTO> memberList = chatRoomInfoRepository.getChatRoomMemberDTOListByChatRoomId(chatRoomId);
            chatRoomInfoDTO.setMembers(memberList);
//            System.out.println("getChatRoomInfoDTOWithMembers: " + memberList);
        }

        return chatRoomInfoDTO;
    }
}
