package com.yongin.complaint.Payload.requset;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignInRequest implements Serializable {
    private static final long serialVersionUID = 5926468583005150707L;
    private String id;
    private String password;
}
