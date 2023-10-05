package com.yongin.complaint.Payload.response;

import com.yongin.complaint.Payload.response.SignUpResponse;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SignInResponse extends SignUpResponse {
    private String token;
    @Builder
    public SignInResponse(boolean success, int code, String msg, String token,MemberInfoResponse memberInfoResponse){
        super(success,code,msg);
        this.token = token;
    }
}
