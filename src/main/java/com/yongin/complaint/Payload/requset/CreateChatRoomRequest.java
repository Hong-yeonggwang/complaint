package com.yongin.complaint.Payload.requset;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CreateChatRoomRequest {
    String chatRoomName;
    Integer maxUsers;
}
