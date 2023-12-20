package com.yongin.complaint.Payload.response;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SignInResponse extends SignUpResponse {
    private String token;

    @Builder
    public SignInResponse(boolean success, int code, String msg, String token) {
        super(success, code, msg);
        this.token = token;
    }
}
