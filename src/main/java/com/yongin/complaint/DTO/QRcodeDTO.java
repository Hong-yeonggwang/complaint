package com.yongin.complaint.DTO;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QRcodeDTO {
    private String category;
    private String qrSerial;
}
