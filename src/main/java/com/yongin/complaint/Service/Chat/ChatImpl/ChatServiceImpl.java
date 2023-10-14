package com.yongin.complaint.Service.Chat.ChatImpl;

import com.yongin.complaint.DTO.ChatRoomInfoDTO;
import com.yongin.complaint.DTO.ChatRoomMemberDTO;
import com.yongin.complaint.JPA.Entity.ChatRoomInfo;
import com.yongin.complaint.JPA.Entity.Member;
import com.yongin.complaint.JPA.Repository.ChatRoomInfoRepository;
import com.yongin.complaint.Service.Chat.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
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
//    List<ChatRoomInfo> roomList; // 리스트 반환용
    List<ChatRoomInfoDTO> roomList; // 리스트 반환용
    Optional<ChatRoomInfo> optionalChatRoomInfo;
    ChatRoomInfo chatRoomInfo;
    ChatRoomInfoRepository chatRoomInfoRepository; //
    List<Member> memberList; // 생성용


    @Autowired
    public ChatServiceImpl(ChatRoomInfoRepository chatRoomInfoRepository){
        this.chatRoomInfoRepository = chatRoomInfoRepository;
    }

    @Override
    public List<ChatRoomInfoDTO> getChatRoomList() {
//        List<ChatRoomInfo> chatRoomList = chatRoomInfoRepository.findAll();;
//
//        List<ChatRoomInfoDTO> result = new ArrayList<>();
//
//        for (ChatRoomInfo chatRoom : chatRoomList) {
//            ChatRoomInfoDTO chatRoomInfoDTO = new ChatRoomInfoDTO(
//                    chatRoom.getChatRoomSeq(),
//                    chatRoom.getChatRoomId(),
//                    chatRoom.getChatRoomName(),
//                    chatRoom.getCurrentNumberOfPeople(),
//                    chatRoom.getChatRoomLimited());
//
////            chatRoomInfoDTO.setChatRoomSeq(chatRoom.getChatRoomSeq());
////            chatRoomInfoDTO.setChatRoomId(chatRoom.getChatRoomId());
////            chatRoomInfoDTO.setChatRoomName(chatRoom.getChatRoomName());
////            chatRoomInfoDTO.setCurrentNumberOfPeople(chatRoom.getCurrentNumberOfPeople());
////            chatRoomInfoDTO.setChatRoomLimited(chatRoom.getChatRoomLimited());
//
//            List<ChatRoomMemberDTO> memberDTOs = new ArrayList<>();
//
//            for (Member member : chatRoom.getMembers()) {
//                ChatRoomMemberDTO memberDTO = new ChatRoomMemberDTO(member.getMemberSeq(), member.getNickName());
//                memberDTOs.add(memberDTO);
//            }
//
//            chatRoomInfoDTO.setMembers(memberDTOs);
//            result.add(chatRoomInfoDTO);
//        }
//
////        for(int i = 0; i < chatRoomList.size(); ++i){
////            for (Member member: chatRoomList.get(i).getMembers()) {
////                System.out.println(member);
////            }
////        }
//
//        for(int i = 0; i < result.size(); ++i){
//            System.out.println(result.get(i));
//
//            for (ChatRoomMemberDTO member: result.get(i).getMembers()) {
//                System.out.println(member);
//
//            }
//        }
//
//        return result;
//        return chatRoomList;
        return null;
    }

    @Override
    @Transactional
    public List<ChatRoomInfoDTO> createChatRoom(ChatRoomInfo newChatRoomInfo, Member myInfo) {
//        roomList = this.getChatRoomList();
//
//        System.out.println(roomList.isEmpty());
//
//        boolean checkNewChatRoomId = false; // ChatRoomId 중복 검사 플래그
//        String uniqueId = new BigInteger(130, secureRandom).toString(32);
//
//        // 이전에 생성된 방이 없으면 검사 안함
//        if (roomList.size() == 0) checkNewChatRoomId = true;
//
//        // 중복 검사 시작
//        while (!checkNewChatRoomId) {
//            for (int i = 0; i < roomList.size(); ++i) {
//                // Id가 중복이면 새로 만들고 처음부터 다시 검사
//                if (uniqueId.equals(roomList.get(i).getChatRoomId())) {
//                    uniqueId = new BigInteger(130, secureRandom).toString(32);
//                    i = 0;
//                }
//                if (i + 1 == roomList.size()) {
//                    checkNewChatRoomId = true;
//                }
//            }
//        }
//
//        memberList = new ArrayList<>(){{
//            add(myInfo);
//        }};
//
//        newChatRoomInfo.setChatRoomId(uniqueId);
//        newChatRoomInfo.setChatRoomCreatedDate(LocalDateTime.now());
//        newChatRoomInfo.setCurrentNumberOfPeople(1);
//        newChatRoomInfo.setMembers(memberList);
//
//        newChatRoomInfo = chatRoomInfoRepository.save(newChatRoomInfo);
//
//        ChatRoomInfoDTO newChatRoomInfoDTO = new ChatRoomInfoDTO(
//                newChatRoomInfo.getChatRoomSeq(),
//                newChatRoomInfo.getChatRoomId(),
//                newChatRoomInfo.getChatRoomName(),
//                newChatRoomInfo.getCurrentNumberOfPeople(),
//                newChatRoomInfo.getChatRoomLimited()
//        );
//
//        newChatRoomInfoDTO.setMembers(newChatRoomInfoDTO.getMembers());
//
//        roomList.add(newChatRoomInfoDTO);
//
//        return roomList;

        return null;
    }

    @Override
    public void enterChatRoom(Long chatRoomSeq) {
//        optionalChatRoomInfo = chatRoomInfoRepository.findById(chatRoomSeq);
//
//        if(optionalChatRoomInfo.isPresent()){
//            chatRoomInfo = optionalChatRoomInfo.get();
//        }
//        else{
//
//        }
    }

    @Override
    public boolean dropChatRoom(ChatRoomInfoDTO chatRoomInfoDTO) {
        boolean result = false;

        return result;
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
