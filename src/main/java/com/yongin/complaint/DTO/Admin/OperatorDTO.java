package com.yongin.complaint.DTO.Admin;

import com.yongin.complaint.JPA.Entity.QRcodeCategory;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OperatorDTO {
    private String name;
    private String phoneNumber;
    private QRcodeCategory qRcodeCategory;

    public OperatorDTO(String name, QRcodeCategory qRcodeCategory, String phoneNumber) {
        this.name = name;
        this.qRcodeCategory = qRcodeCategory;
        this.phoneNumber = phoneNumber;
    }
}

