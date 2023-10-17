package com.yongin.complaint.Payload.requset;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CreateChatRoomRequest {
    String chatRoomName;
    Integer chatRoomLimited;
}
