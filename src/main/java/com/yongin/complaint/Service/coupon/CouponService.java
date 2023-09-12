package com.yongin.complaint.Service.coupon;

import com.yongin.complaint.Payload.response.CreateCouponResponse;

public interface CouponService {

    CreateCouponResponse createCoupon(String category, String name);


}
