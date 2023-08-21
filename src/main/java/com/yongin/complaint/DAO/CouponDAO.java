package com.yongin.complaint.DAO;

import com.yongin.complaint.JPA.Entity.Coupon;
import com.yongin.complaint.JPA.Entity.QRcodeCategory;


public interface CouponDAO {
    Coupon CouponExist(String name);

    QRcodeCategory getPrice(String category , String name);
}
