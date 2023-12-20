package com.yongin.complaint.DTO.Member;

import com.yongin.complaint.JPA.Entity.QRcodeCategory;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class QRcodeLogDTO {
    private Long seq;
    private String serial;
    private LocalDateTime usedDate;
    private QRcodeCategory category;

    public QRcodeLogDTO(
            Long seq,
            String serial,
            LocalDateTime usedDate,
            QRcodeCategory category
    ) {
        this.seq = seq;
        this.serial = serial;
        this.usedDate = usedDate;
        this.category = category;
    }

}
