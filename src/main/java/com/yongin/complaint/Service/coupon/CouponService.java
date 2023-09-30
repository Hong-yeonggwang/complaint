package com.yongin.complaint.Service.coupon;

import com.yongin.complaint.JPA.Entity.Member;
import com.yongin.complaint.Payload.response.CreateCouponResponse;
import com.yongin.complaint.Payload.response.QRcodeResponse;

public interface CouponService {

    CreateCouponResponse createCoupon(String category, String name);

    QRcodeResponse useCoupon(String couponSerial, Member member);

}
