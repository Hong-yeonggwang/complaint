package com.yongin.complaint.Payload.requset;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Getter
@Setter
@ToString
public class UserInfoUpdateRequest {
    @NotNull(message = "닉네임은 필수 사항입니다.")
    private String nickName;
    @NotNull(message = "학과는 필수 사항입니다.")
    private String major;
    @NotNull(message = "생년월일은 필수 사항입니다.")
    private LocalDate birth;
    @Pattern(regexp = "^(010|01[1-9])\\d{3,4}\\d{4}$", message = "전화번호 10자리만 입력하세요")
    private String phoneNumber;
}
