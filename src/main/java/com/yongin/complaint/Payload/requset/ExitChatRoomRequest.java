package com.yongin.complaint.Payload.requset;

import lombok.Getter;

@Getter
public class ExitChatRoomRequest {
    Long chatRoomSeq;
    int currentNumberOfPeople;
}
