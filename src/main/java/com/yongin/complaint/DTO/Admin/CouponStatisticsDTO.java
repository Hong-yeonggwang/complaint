package com.yongin.complaint.DTO.Admin;

import com.yongin.complaint.JPA.Entity.Coupon;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CouponStatisticsDTO {
    private String status;
    private String placeName;
    private Long count;

    public CouponStatisticsDTO(String status,Long count,String placeName){
        this.status = status;
        this.count = count;
        this.placeName = placeName;
    }
    public CouponStatisticsDTO(Long count,String placeName){
        this.count = count;
        this.placeName = placeName;
    }
}
