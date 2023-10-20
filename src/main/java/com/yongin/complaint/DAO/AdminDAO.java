package com.yongin.complaint.DAO;

import com.yongin.complaint.DTO.Admin.CategoryUpdateDTO;
import com.yongin.complaint.JPA.Entity.Coupon;
import com.yongin.complaint.JPA.Entity.Member;
import com.yongin.complaint.JPA.Entity.QRcodeCategory;
import com.yongin.complaint.Payload.response.Admin.CouponUseRateResponse;

import java.util.List;

public interface AdminDAO {
    List<QRcodeCategory> getAllCategory();

    void updateCategory(String placeName, String name, CategoryUpdateDTO updateInfo);

    void addCategory(CategoryUpdateDTO updateInfo);

    void deleteCategory(String name);

    List<CouponUseRateResponse> getUseRateCoupon();

    List<CouponUseRateResponse> getUseRateQRcode();

    Long getUserCount();

    Long getPlaceCount();

    Long getTodayUsedQRcode(String today);
    Long getTodayCreatedQRcode(String today);
    List<QRcodeCategory> getCategoryInfo();

    List<Coupon> getCouponList();

    Long getRemainQrcodeCount();

}
