package com.yongin.complaint.Payload.requset;

import lombok.*;

@Getter
@Setter
@ToString
public class SendingMessageRequest {
    private String roomId;
    private String sender;
    private String message;
}
