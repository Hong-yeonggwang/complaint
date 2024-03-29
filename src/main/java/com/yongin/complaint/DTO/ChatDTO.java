package com.yongin.complaint.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChatDTO {
    private String chatMessage;
    private LocalDateTime chatHistoryTime;
    private String chatUserId;
    private String chatRoomId;
}
