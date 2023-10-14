package com.yongin.complaint.DTO;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatRoomInfoDTO {

    private Long chatRoomSeq;
    private String chatRoomId;
    private String chatRoomName;
    private Integer currentNumberOfPeople;
    private Integer chatRoomLimited;
    private List<ChatRoomMemberDTO> members;

    public ChatRoomInfoDTO(Long chatRoomSeq, String chatRoomId, String chatRoomName, Integer currentNumberOfPeople, Integer chatRoomLimited) {
        this.chatRoomSeq = chatRoomSeq;
        this.chatRoomId = chatRoomId;
        this.chatRoomName = chatRoomName;
        this.currentNumberOfPeople = currentNumberOfPeople;
        this.chatRoomLimited = chatRoomLimited;
    }
}
