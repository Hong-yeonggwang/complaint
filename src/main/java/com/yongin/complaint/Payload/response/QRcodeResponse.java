package com.yongin.complaint.Payload.response;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QRcodeResponse {
    private String msg;
    private String qRcode;
}
