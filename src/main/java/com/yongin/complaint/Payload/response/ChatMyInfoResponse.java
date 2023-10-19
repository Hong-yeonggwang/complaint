package com.yongin.complaint.Payload.response;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class ChatMyInfoResponse {
    Long memberSeq;
    String nickName;
}
