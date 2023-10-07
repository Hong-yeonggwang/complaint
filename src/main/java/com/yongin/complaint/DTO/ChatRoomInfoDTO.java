package com.yongin.complaint.DTO;

import java.time.LocalDateTime;
import java.util.List;

public class ChatRoomInfoDTO {

    private String chatRoomId;
    private String chatRoomName;

    private List<String> members;
    private Integer currentNumBerOfPeople;
    private Integer chatRoomLimited;

    private LocalDateTime chatRoomCreatedDate;
    private LocalDateTime chatRoomDeletedDate;

    private boolean state;
}
