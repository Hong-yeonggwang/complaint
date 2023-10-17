package com.yongin.complaint.DTO.Admin;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class QRcodeStatisticsDTO {
    private String status;
    private String placeName;
    private Long count;

    public QRcodeStatisticsDTO(String status, Long count, String placeName){
        this.status = status;
        this.count = count;
        this.placeName = placeName;
    }
    public QRcodeStatisticsDTO(Long count, String placeName){
        this.count = count;
        this.placeName = placeName;
    }
}
