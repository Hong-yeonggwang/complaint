package com.yongin.complaint.Payload.requset;

import lombok.Getter;

@Getter
public class CreateChatRoomRequest {
    String chatRoomName;
    Integer chatRoomLimited;
}
