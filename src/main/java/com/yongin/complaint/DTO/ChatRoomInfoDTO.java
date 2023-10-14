package com.yongin.complaint.DTO;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChatRoomInfoDTO {

    private Long chatRoomSeq;
    private String chatRoomId;
    private String chatRoomName;

//    private List<String> members;
    private Integer currentNumberOfPeople;
    private Integer chatRoomLimited;

//    private LocalDateTime chatRoomCreatedDate;
//    private LocalDateTime chatRoomDeletedDate;

//    private boolean state;
    private List<ChatRoomMemberDTO> members;

    public ChatRoomInfoDTO(Long chatRoomSeq, String chatRoomId, String chatRoomName, Integer currentNumberOfPeople, Integer chatRoomLimited) {
        this.chatRoomSeq = chatRoomSeq;
        this.chatRoomId = chatRoomId;
        this.chatRoomName = chatRoomName;
        this.currentNumberOfPeople = currentNumberOfPeople;
        this.chatRoomLimited = chatRoomLimited;
    }
}
