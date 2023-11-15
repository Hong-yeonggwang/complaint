package com.yongin.complaint.Payload.response.Admin;

import com.yongin.complaint.JPA.Entity.Coupon;
import com.yongin.complaint.JPA.Entity.QRcodeCategory;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ServiceStatusResponse {
    private List<CouponUseRateResponse> couponUseRate;
    private List<CouponUseRateResponse> qrcodeUSeRate;
    private Long userCount;
    private Long placeCount;
    private Long todayUsedCount;
    private Long todayCreatedCount;
    private Long remainQrcodeCount;
    private List<QRcodeCategory> categoryInfo;
    private List<Coupon> couponList;
    private Long usingCategory;
}
