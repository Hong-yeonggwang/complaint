package com.yongin.complaint.DTO.coupon.coupon;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateCouponResponse {
    private String msg;
    private String coupon;
    private String name;
    private Integer price;
}
