package com.yongin.complaint.DTO;

import com.yongin.complaint.JPA.Entity.QRcodeCategory;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QRcodeDTO {
    private QRcodeCategory category;
    private String qrSerial;
}
