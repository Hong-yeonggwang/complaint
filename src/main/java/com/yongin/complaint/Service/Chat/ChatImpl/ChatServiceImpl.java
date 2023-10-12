package com.yongin.complaint.Service.Chat.ChatImpl;

import com.yongin.complaint.DAO.ChatRoomDAO;
import com.yongin.complaint.DTO.ChatRoomInfoDTO;
import com.yongin.complaint.JPA.Entity.ChatRoomInfo;
import com.yongin.complaint.Payload.response.ChatRoomInfoResponse;
import com.yongin.complaint.Service.Chat.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ChatServiceImpl implements ChatService {
    SecureRandom secureRandom  = new SecureRandom(); // 채팅방 고유 ID를 생성하기 위한 랜덤 객체
    List<ChatRoomInfo> roomList = null; // 리스트 반환용
    ChatRoomDAO chatRoomDAO = null;


    @Autowired
    public ChatServiceImpl(ChatRoomDAO chatRoomDAO){
        this.chatRoomDAO = chatRoomDAO;
    }

    @Override
    @Transactional
    public List<ChatRoomInfo> CreateChatRoom(ChatRoomInfoDTO newChatRoomInfoDTO) {
        roomList = this.getChatRoomList();
        boolean checkNewChatRoomId = false; // ChatRoomId 중복 검사 플래그
        String uniqueId = new BigInteger(130, secureRandom).toString(32);

        // 이전에 생성된 방이 없으면 검사 안함
        if (roomList.size() == 0) checkNewChatRoomId = true;
        
        // 중복 검사 시작
        while (!checkNewChatRoomId) {
            for (int i = 0; i < roomList.size(); ++i) {
                // Id가 중복이면 새로 만들고 처음부터 다시 검사
                if (uniqueId.equals(roomList.get(i).getChatRoomId())) {
                    uniqueId = new BigInteger(130, secureRandom).toString(32);
                    i = 0;
                }
                if (i + 1 == roomList.size()) {
                    checkNewChatRoomId = true;
                }
            }
        }

        newChatRoomInfoDTO.setChatRoomId(uniqueId);
        newChatRoomInfoDTO.setChatRoomCreatedDate(LocalDateTime.now());
//            newChatRoomInfoDTO.setChatRoomName(userId); // 방 만든 사람

        ;
        if(chatRoomDAO.createChatRoom(newChatRoomInfoDTO)){
            chatRoomDAO.getLastestChatRoomInfo();
        }

        return roomList;
    }

    @Override
    public boolean dropChatRoom(ChatRoomInfoDTO chatRoomInfoDTO) {
        boolean result = false;

        return result;
    }

    @Override
    public List<ChatRoomInfo> getChatRoomList() {
        List<ChatRoomInfo> chatRoomList = null;

        return chatRoomList;
    }

    @Override
    public List<ChatRoomInfo> getMyChatRoomList(String userId) {
        return null;
    }
}
