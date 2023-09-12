package com.yongin.complaint.Payload.response;

import com.yongin.complaint.QRcodeGenerater.QRcodeGenerater;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QRcodeResponse {
    private String msg;
    private QRcodeGenerater qRcode;
}
