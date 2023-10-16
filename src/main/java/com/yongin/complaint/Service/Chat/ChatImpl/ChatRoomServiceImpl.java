package com.yongin.complaint.Service.Chat.ChatImpl;

import com.yongin.complaint.DAO.ChatRoomDAO;
import com.yongin.complaint.DTO.ChatRoomInfoDTO;
import com.yongin.complaint.DTO.ChatRoomMemberDTO;
import com.yongin.complaint.JPA.Entity.ChatRoomInfo;
import com.yongin.complaint.JPA.Entity.Member;
import com.yongin.complaint.JPA.Repository.ChatRoomInfoRepository;
import com.yongin.complaint.Service.Chat.ChatRoomService;
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
public class ChatRoomServiceImpl implements ChatRoomService {
    SecureRandom secureRandom  = new SecureRandom(); // 채팅방 고유 ID를 생성하기 위한 랜덤 객체
    ChatRoomInfoRepository chatRoomInfoRepository; //
    ChatRoomDAO chatRoomDAOImpl;


    @Autowired
    public ChatRoomServiceImpl(ChatRoomInfoRepository chatRoomInfoRepository, ChatRoomDAO chatRoomDAOImpl){
        this.chatRoomInfoRepository = chatRoomInfoRepository;
        this.chatRoomDAOImpl = chatRoomDAOImpl;
    }

    @Override
    public List<ChatRoomInfoDTO> getChatRoomInfoDTOList() {
        List<ChatRoomInfo> chatRoomInfoList = chatRoomInfoRepository.findAll();;

        List<ChatRoomInfoDTO> chatRoomInfoDTOList = new ArrayList<>();

        ChatRoomInfoDTO chatRoomInfoDTO;
        for (ChatRoomInfo chatRoom : chatRoomInfoList) {
            chatRoomInfoDTO = new ChatRoomInfoDTO().builder()
                    .chatRoomSeq(chatRoom.getChatRoomSeq())
                    .chatRoomId(chatRoom.getChatRoomId())
                    .chatRoomName(chatRoom.getChatRoomName())
                    .currentNumberOfPeople(chatRoom.getCurrentNumberOfPeople())
                    .chatRoomLimited(chatRoom.getChatRoomLimited())
                    .build();

            List<ChatRoomMemberDTO> memberDTOs = new ArrayList<>();

            for (Member member : chatRoom.getMembers()) {
                ChatRoomMemberDTO memberDTO = new ChatRoomMemberDTO(member.getMemberSeq(), member.getNickName());
                memberDTOs.add(memberDTO);
            }

            chatRoomInfoDTO.setMembers(memberDTOs);
            chatRoomInfoDTOList.add(chatRoomInfoDTO);
        }

//        for(int i = 0; i < chatRoomList.size(); ++i){
//            for (Member member: chatRoomList.get(i).getMembers()) {
//                System.out.println(member);
//            }
//        }

        for(int i = 0; i < chatRoomInfoDTOList.size(); ++i){
            System.out.println(chatRoomInfoDTOList.get(i));

            for (ChatRoomMemberDTO member: chatRoomInfoDTOList.get(i).getMembers()) {
                System.out.println(member);

            }
        }

        return chatRoomInfoDTOList;
    }

    @Override
    @Transactional
    public List<ChatRoomInfoDTO> createChatRoom(ChatRoomInfo newChatRoomInfo, Member myInfo) {
        List<ChatRoomInfoDTO> chatRoomInfoDTOList = this.getChatRoomInfoDTOList();

        System.out.println(chatRoomInfoDTOList.isEmpty());

        boolean checkNewChatRoomId = false; // ChatRoomId 중복 검사 플래그
        String uniqueId = new BigInteger(130, secureRandom).toString(32);

        // 이전에 생성된 방이 없으면 검사 안함
        if (chatRoomInfoDTOList.size() == 0) checkNewChatRoomId = true;

        // 중복 검사 시작
        while (!checkNewChatRoomId) {
            for (int i = 0; i < chatRoomInfoDTOList.size(); ++i) {
                // Id가 중복이면 새로 만들고 처음부터 다시 검사
                if (uniqueId.equals(chatRoomInfoDTOList.get(i).getChatRoomId())) {
                    uniqueId = new BigInteger(130, secureRandom).toString(32);
                    i = 0;
                }
                if (i + 1 == chatRoomInfoDTOList.size()) {
                    checkNewChatRoomId = true;
                }
            }
        }

        List<Member> memberList = new ArrayList<>(){{
            add(myInfo);
        }};

        newChatRoomInfo.setChatRoomId(uniqueId);
        newChatRoomInfo.setChatRoomCreatedDate(LocalDateTime.now());
        newChatRoomInfo.setCurrentNumberOfPeople(1);
        newChatRoomInfo.setMembers(memberList);

        newChatRoomInfo = chatRoomInfoRepository.save(newChatRoomInfo);

        ChatRoomInfoDTO newChatRoomInfoDTO = new ChatRoomInfoDTO().builder()
                .chatRoomSeq(newChatRoomInfo.getChatRoomSeq())
                .chatRoomId(newChatRoomInfo.getChatRoomId())
                .chatRoomName(newChatRoomInfo.getChatRoomName())
                .currentNumberOfPeople(newChatRoomInfo.getCurrentNumberOfPeople())
                .chatRoomLimited(newChatRoomInfo.getChatRoomLimited())
                .build();

        newChatRoomInfoDTO.setMembers(newChatRoomInfoDTO.getMembers());

        chatRoomInfoDTOList.add(newChatRoomInfoDTO);

        return chatRoomInfoDTOList;
    }

    public ChatRoomInfoDTO enterChatRoom(ChatRoomInfoDTO chatRoomInfoDTO, Member myInfo){
        List<ChatRoomMemberDTO> chatRoomMemberDTOList = chatRoomInfoRepository.getChatRoomMemberDTOListByChatRoomId(chatRoomInfoDTO.getChatRoomId());

        System.out.println(chatRoomMemberDTOList);

        Long mySeq = myInfo.getMemberSeq();

        ChatRoomMemberDTO chatRoomMemberDTO;
        int chatRoomMemberDTOListSize = chatRoomMemberDTOList.size();

        for(int i = 0; i < chatRoomMemberDTOListSize; ++i){
            chatRoomMemberDTO =  chatRoomMemberDTOList.get(i);

            if(mySeq == chatRoomMemberDTO.getMemberSeq()){
                // 바로 채팅방 이동
                chatRoomInfoDTO.setMembers(chatRoomMemberDTOList);
                break;
            }

            // test 아직 안함 controller에서 소켓 송신 확인되면 test
            else if(i + 1 == chatRoomMemberDTOListSize){
                // 채팅방에 내 정보 추가하고
                
                // DB 업데이트
                ChatRoomInfo chatRoomInfo = chatRoomInfoRepository.findByChatRoomSeq(chatRoomInfoDTO.getChatRoomSeq());
                chatRoomInfo.getMembers().add(myInfo);
                chatRoomInfo = chatRoomInfoRepository.save(chatRoomInfo);

                System.out.println("chatRoomInfo: " + chatRoomInfo);

                // DTO 정보 업데이트
                chatRoomInfoDTO.getMembers().add(new ChatRoomMemberDTO(myInfo.getMemberSeq(), myInfo.getNickName()));
                System.out.println("chatRoomInfoDTO: " + chatRoomInfoDTO);

                // 채팅방 이동
            }
        }

        return chatRoomInfoDTO;
    }


//    @Override
//    public ChatRoomInfoDTO getChatRoomInfoDTObySeq(String chatRoomId) {
////        optionalChatRoomInfo = chatRoomInfoRepository.findById(chatRoomSeq);
////
////        if(optionalChatRoomInfo.isPresent()){
////            chatRoomInfo = optionalChatRoomInfo.get();
////        }
////        else{
////
////        }
//        chatRoomInfoDTO = chatRoomDAOImpl.findChatRoomsWithMembers(chatRoomId);
//
//        return chatRoomInfoDTO;
//    }

    @Override
    public boolean dropChatRoom(ChatRoomInfoDTO chatRoomInfoDTO) {
        boolean result = false;

        return result;
    }

    @Override
    public List<ChatRoomInfo> getMyChatRoomList(String userId) {
        return null;
    }

//    @Override
//    public boolean checkChatRoomId(String chatRoomId) {
//        boolean result = chatRoomInfoRepository.findByChatRoomId(chatRoomId);
////        this.getChatRoomList();
//
//        return result;
//    }
}