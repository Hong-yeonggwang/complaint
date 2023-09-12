package com.yongin.complaint.Payload.requset;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class JwtRequest implements Serializable {
    private static final long serialVersionUID = 5926468583005150707L;

    private String username;
    private String password;
}
