package com.yongin.complaint.Payload.requset;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;


@Getter
@Setter
@ToString
public class SignUpRequest {
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[0-9])[a-zA-Z0-9]{8,16}$", message = "아이디 형식에 맞지 않습니다.")
    private String id;

    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,16}$", message = "비밀번호 형식에 맞지 않습니다.")
    private String password;

    @NotEmpty(message = "이름 입력은 필수 입니다.")
    private String name;

    @NotNull(message = "생년월일을 입력해주세요")
    private LocalDate birth;

    private String major;

    @NotNull(message = "필수 입력사항입니다.")
    private String nickName;

    @Pattern(regexp = "^(010|01[1-9])\\d{3,4}\\d{4}$", message = "전화번호 10자리만 입력하세요")
    private String phoneNumber;

    @Pattern(regexp = "^[a-zA-Z0-9_+&*-]+(?:\\." +
            "[a-zA-Z0-9_+&*-]+)*@" +
            "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
            "A-Z]{2,7}$")
    @NotNull(message = "필수 입력사항입니다.")
    private String email;

}
