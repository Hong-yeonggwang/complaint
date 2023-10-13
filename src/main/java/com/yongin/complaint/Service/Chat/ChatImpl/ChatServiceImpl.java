package com.yongin.complaint.Service.Chat.ChatImpl;

import com.yongin.complaint.DTO.ChatRoomInfoDTO;
import com.yongin.complaint.JPA.Entity.ChatRoomInfo;
import com.yongin.complaint.JPA.Entity.Member;
import com.yongin.complaint.JPA.Repository.ChatRoomInfoRepository;
import com.yongin.complaint.Service.Chat.ChatService;
import com.yongin.complaint.config.security.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ChatServiceImpl implements ChatService {
    SecureRandom secureRandom  = new SecureRandom(); // 채팅방 고유 ID를 생성하기 위한 랜덤 객체
    List<ChatRoomInfo> roomList; // 리스트 반환용
    Optional<ChatRoomInfo> optionalChatRoomInfo;
    ChatRoomInfo chatRoomInfo;
    ChatRoomInfoRepository chatRoomInfoRepository;
    List<Member> memberList;


    @Autowired
    public ChatServiceImpl(ChatRoomInfoRepository chatRoomInfoRepository){
        this.chatRoomInfoRepository = chatRoomInfoRepository;
    }

    @Override
    @Transactional
    public List<ChatRoomInfo> createChatRoom(ChatRoomInfo newChatRoomInfo, Member myInfo) {
        memberList = new ArrayList<>(){{
            add(myInfo);
        }};

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

        newChatRoomInfo.setChatRoomId(uniqueId);
        newChatRoomInfo.setChatRoomCreatedDate(LocalDateTime.now());
        newChatRoomInfo.setCurrentNumBerOfPeople(1);
        newChatRoomInfo.setMembers(memberList);

        roomList.add(chatRoomInfoRepository.save(newChatRoomInfo));

        return roomList;
    }

    @Override
    public void enterChatRoom(Long chatRoomSeq) {
        optionalChatRoomInfo = chatRoomInfoRepository.findById(chatRoomSeq);

        if(optionalChatRoomInfo.isPresent()){
            chatRoomInfo = optionalChatRoomInfo.get();
        }
        else{

        }
    }

    @Override
    public boolean dropChatRoom(ChatRoomInfoDTO chatRoomInfoDTO) {
        boolean result = false;

        return result;
    }

    @Override
    public List<ChatRoomInfo> getChatRoomList() {
        List<ChatRoomInfo> chatRoomList = chatRoomInfoRepository.findAll();;

        return chatRoomList;
    }

    @Override
    public List<ChatRoomInfo> getMyChatRoomList(String userId) {
        return null;
    }

    @Override
    public boolean checkChatRoomId(String chatRoomId) {
        boolean result = chatRoomInfoRepository.findByChatRoomId(chatRoomId);
//        this.getChatRoomList();

        return result;
    }
}
