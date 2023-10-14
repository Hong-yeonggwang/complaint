package com.yongin.complaint.DTO;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChatRoomMemberDTO {
    private Long memberSeq;
    private String nickName;
}
