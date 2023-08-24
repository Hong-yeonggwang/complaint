package com.yongin.complaint.DTO.QRcode;

import com.yongin.complaint.QRcodeGenerater.QRcodeGenerater;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QRcodeResponseDTO {
    private String msg;
    private QRcodeGenerater qRcode;
}
