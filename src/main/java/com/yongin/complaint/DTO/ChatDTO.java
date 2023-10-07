package com.yongin.complaint.DTO;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChatDTO {
    private String chatMessage;
    private LocalDateTime chatHistoryTime;
    private String chatUserId;
    private String chatRoomId;
}
