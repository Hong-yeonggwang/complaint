package com.yongin.complaint.Payload.response;

import com.yongin.complaint.DTO.ChatRoomInfoDTO;
import com.yongin.complaint.DTO.ChatRoomMemberDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EnterChatRoomResponse {
    boolean isChatRoomExist;
    boolean alreadyEnter;
    boolean isChatRoomRemaining;
    ChatRoomInfoDTO chatRoomInfoDTO;
    ChatRoomMemberDTO myInfo;
}
