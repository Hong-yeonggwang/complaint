package com.yongin.complaint.Service.Chat;

import com.yongin.complaint.DTO.ChatRoomInfoDTO;
import com.yongin.complaint.JPA.Entity.ChatRoomInfo;
import com.yongin.complaint.JPA.Entity.Member;
import com.yongin.complaint.Payload.response.ChatRoomInfoResponse;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface ChatService {

    List<ChatRoomInfo> createChatRoom(ChatRoomInfo newChatRoomInfo, Member myInfo);

    void enterChatRoom(Long chatRoomSeq);

    boolean dropChatRoom(ChatRoomInfoDTO chatRoomInfoDTO);

    List<ChatRoomInfo> getChatRoomList();

    List<ChatRoomInfo> getMyChatRoomList(String userId);

    boolean checkChatRoomId(String chatRoomId);
}
