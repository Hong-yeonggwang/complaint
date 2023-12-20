package com.yongin.complaint.DTO.Admin;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class QRcodeDateDTO {
    private String time;
    private Long count;

    public QRcodeDateDTO(Long count, String time) {
        this.time = time;
        this.count = count;
    }
}
