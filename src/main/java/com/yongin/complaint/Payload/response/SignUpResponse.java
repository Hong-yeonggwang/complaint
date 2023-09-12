package com.yongin.complaint.Payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SignUpResponse {
    private boolean success;
    private int code;
    private String msg;
}
