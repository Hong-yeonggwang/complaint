package com.yongin.complaint.Payload.response;

import lombok.Setter;

import java.time.LocalDateTime;

@Setter
public class SendingMessageResponse {
    private String roomId;
    private String sender;
    private String message;
    private LocalDateTime timestamp;

}
