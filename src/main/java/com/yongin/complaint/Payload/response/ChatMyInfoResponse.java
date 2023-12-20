package com.yongin.complaint.Payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class ChatMyInfoResponse {
    Long memberSeq;
    String nickName;
}
