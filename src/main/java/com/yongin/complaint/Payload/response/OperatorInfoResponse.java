package com.yongin.complaint.Payload.response;

import com.yongin.complaint.JPA.Entity.QRcodeCategory;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OperatorInfoResponse {
    private String name;
    private String phoneNumber;
    private QRcodeCategory category;
}
