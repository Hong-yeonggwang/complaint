package com.yongin.complaint.Payload.response;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberInfoResponse {
    private String name;
    private LocalDate birth;
    private String nickName;
    private String phoneNumber;
    private String major;
}
