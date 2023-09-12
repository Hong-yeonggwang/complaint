package com.yongin.complaint.Payload.response;

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
