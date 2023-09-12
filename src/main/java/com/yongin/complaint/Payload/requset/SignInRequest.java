package com.yongin.complaint.Payload.requset;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignInRequest {
    private String id;
    private String password;
}
