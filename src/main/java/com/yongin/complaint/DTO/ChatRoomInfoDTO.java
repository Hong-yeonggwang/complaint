package com.yongin.complaint.DTO;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChatRoomInfoDTO {

    private Integer chatRoomNumber;
    private String chatRoomId;
    private String chatRoomName;

//    private List<String> members;
    private Integer currentNumBerOfPeople;
    private Integer chatRoomLimited;

    private LocalDateTime chatRoomCreatedDate;
    private LocalDateTime chatRoomDeletedDate;

    private boolean state;
}
