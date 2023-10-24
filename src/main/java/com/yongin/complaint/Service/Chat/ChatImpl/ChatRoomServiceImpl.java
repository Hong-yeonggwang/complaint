package com.yongin.complaint.Service.Chat.ChatImpl;

import com.yongin.complaint.DAO.ChatRoomDAO;
import com.yongin.complaint.DTO.ChatRoomInfoDTO;
import com.yongin.complaint.DTO.ChatRoomMemberDTO;
import com.yongin.complaint.JPA.Entity.ChatRoomInfo;
import com.yongin.complaint.JPA.Entity.Member;
import com.yongin.complaint.JPA.Repository.ChatRoomInfoRepository;
import com.yongin.complaint.Payload.requset.ExitChatRoomRequest;
import com.yongin.complaint.Payload.response.EnterChatRoomResponse;
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


    @Override
    @Transactional
    public EnterChatRoomResponse enterChatRoom(String chatRoomId, Member myInfo){
        EnterChatRoomResponse enterChatRoomResponse = new EnterChatRoomResponse();
        ChatRoomInfoDTO chatRoomInfoDTO = chatRoomDAOImpl.getChatRoomInfoDTOWithMembers(chatRoomId);

        if(chatRoomInfoDTO != null){ // 채팅방 존재
            enterChatRoomResponse.setChatRoomExist(true);

            int currentNumberOfPeople = chatRoomInfoDTO.getCurrentNumberOfPeople();
            ChatRoomMemberDTO myInfoDTO = new ChatRoomMemberDTO(myInfo.getMemberSeq(), myInfo.getNickName());
            enterChatRoomResponse.setMyInfo(myInfoDTO);

            List<ChatRoomMemberDTO> chatRoomMemberDTOList = chatRoomInfoDTO.getMembers();
            System.out.println(chatRoomMemberDTOList);

            // 내가 존재하는 방 검사용
            int chatRoomMemberDTOListSize = chatRoomMemberDTOList.size();
            ChatRoomMemberDTO chatRoomMemberDTO;
            Long mySeq = myInfo.getMemberSeq();

            for(int i = 0; i < chatRoomMemberDTOListSize; ++i){
                chatRoomMemberDTO =  chatRoomMemberDTOList.get(i);

                if(mySeq == chatRoomMemberDTO.getMemberSeq()){ // 이미 내가 존재하는 방이면
                    // 채팅방 정보 등록
                    chatRoomInfoDTO.setMembers(chatRoomMemberDTOList);
                    enterChatRoomResponse.setChatRoomInfoDTO(chatRoomInfoDTO);
                    enterChatRoomResponse.setAlreadyEntered(true);

                    // 바로 채팅방 이동
                    break;
                }
                else if(i + 1 == chatRoomMemberDTOListSize){ // 처음 들어가는 방이면 빈 자리 검사
                    if(currentNumberOfPeople < chatRoomInfoDTO.getChatRoomLimited()){ // 채팅방 빈 자리 존재
                        enterChatRoomResponse.setChatRoomRemaining(true);

                        // 채팅방에 내 정보 추가하고
                        System.out.println("service enterChatRoom:" + chatRoomInfoDTO.getChatRoomSeq());

                        // DB 업데이트
                        ChatRoomInfo chatRoomInfo = chatRoomInfoRepository.findByChatRoomSeq(chatRoomInfoDTO.getChatRoomSeq());
                        chatRoomInfo.getMembers().add(myInfo);
                        chatRoomInfo.setCurrentNumberOfPeople(currentNumberOfPeople+1);
                        chatRoomInfo = chatRoomInfoRepository.save(chatRoomInfo);

                        System.out.println("chatRoomInfo: " + chatRoomInfo);

                        // DTO 정보 업데이트
                        chatRoomInfoDTO.getMembers().add(new ChatRoomMemberDTO(myInfo.getMemberSeq(), myInfo.getNickName()));
                        chatRoomInfoDTO.setCurrentNumberOfPeople(currentNumberOfPeople+1);
                        System.out.println("chatRoomInfoDTO: " + chatRoomInfoDTO);

                        // 업데이트 된 채팅방 정보 등록
                        enterChatRoomResponse.setChatRoomInfoDTO(chatRoomInfoDTO);
                        // 채팅방 이동
                    }
                    else // 채팅방 빈 자리 X
                        enterChatRoomResponse.setChatRoomRemaining(false);
                }
            }
        }
        else // 채팅방 존재 X
            enterChatRoomResponse.setChatRoomExist(false);

        return enterChatRoomResponse;
    }

    @Override
    @Transactional
    public void exitChatRoom(Long chatRoomSeq, int currentNumberOfPeople, Member myInfo) {
        Optional<ChatRoomInfo> optionalChatRoomInfo = chatRoomInfoRepository.findById(chatRoomSeq);
        ChatRoomInfo chatRoomInfo = optionalChatRoomInfo.get();
        List<Member> memberList =  chatRoomInfo.getMembers();

        for(Member member : memberList){
            if(member.getMemberSeq() == myInfo.getMemberSeq()){
                chatRoomInfo.setCurrentNumberOfPeople(chatRoomInfo.getCurrentNumberOfPeople()-1);
                memberList.remove(member);
                break;
            }
        }

        chatRoomInfo.setMembers(memberList);
        chatRoomInfoRepository.save(chatRoomInfo);
    }

    @Override
    @Transactional
    public void deleteChatRoom(Long chatRoomSeq) {
        Optional<ChatRoomInfo> optionalChatRoomInfo = chatRoomInfoRepository.findById(chatRoomSeq);
        ChatRoomInfo chatRoomInfo = optionalChatRoomInfo.get();
        chatRoomInfoRepository.delete(chatRoomInfo);
    }

    @Override
    public ChatRoomInfoDTO refreshChatRoomInfo(String chatRoomId) {
        return chatRoomInfoRepository.getChatRoomInfoDTOByChatRoomId(chatRoomId);
    }

    @Override
    public List<ChatRoomInfoDTO> getMyChatRoomInfoDTOList(Member myInfo) {
        return chatRoomInfoRepository.getChatRoomInfoDTOListByMemberSeq(myInfo.getMemberSeq());
    }

}