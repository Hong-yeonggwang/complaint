package com.yongin.complaint.Service.coupon;

import com.yongin.complaint.DTO.coupon.CreateCouponResponse;

public interface CouponService {

    CreateCouponResponse createCoupon(String category, String name);


}
