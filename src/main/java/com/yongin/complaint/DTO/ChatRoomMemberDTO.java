package com.yongin.complaint.DTO;

import lombok.*;

@Getter
@Setter
@ToString
public class ChatRoomMemberDTO {
    private Long memberSeq;
    private String nickName;

    public ChatRoomMemberDTO(Long memberSeq, String nickName){
        this.memberSeq = memberSeq;
        this.nickName = nickName;
    }
}
