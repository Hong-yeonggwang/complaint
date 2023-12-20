package com.yongin.complaint.DTO.Admin;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserInfoDTO {
    private String name;
    private String nickName;
    private String major;
    private String phoneNumber;
    private String email;

    public UserInfoDTO(String name, String nickName, String major, String phoneNumber, String email) {
        this.name = name;
        this.nickName = nickName;
        this.major = major;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }
}

