package com.yongin.complaint.Payload.requset;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Setter
@ToString
public class UserInfoUpdateRequest {
    @Size(min=3, max=7)
    private String nickName;
    private String major;
    private LocalDate birth;
    @Pattern(regexp = "^(010|01[1-9])\\d{3,4}\\d{4}$", message = "전화번호 10자리만 입력하세요")
    private String phoneNumber;
}
