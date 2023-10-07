package com.yongin.complaint.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ChatDTO {
    private String chatMessage;
    private LocalDateTime chatHistoryTime;
    private String chatUserId;
    private String chatRoomId;
}
