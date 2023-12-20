package com.yongin.complaint.DAO;

import com.yongin.complaint.DTO.Admin.CategoryUpdateDTO;
import com.yongin.complaint.DTO.Admin.OperatorDTO;
import com.yongin.complaint.DTO.Admin.QRcodeDateDTO;
import com.yongin.complaint.DTO.Admin.UserInfoDTO;
import com.yongin.complaint.JPA.Entity.Coupon;
import com.yongin.complaint.JPA.Entity.Place;
import com.yongin.complaint.JPA.Entity.QRcodeCategory;
import com.yongin.complaint.Payload.response.Admin.CategoryDTO;
import com.yongin.complaint.Payload.response.Admin.CouponUseRateResponse;

import java.util.List;

public interface AdminDAO {
    List<QRcodeCategory> getAllCategory();

    List<Place> getAllPlace();


    void updateCategory(CategoryUpdateDTO categoryUpdateDTO);

    void updatePlace(Long seq, String name);

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

    List<OperatorDTO> getOperatorList();

    CategoryDTO getCategoryList();

    void savePlace(Place entity);


    Long getUsingCategory();

    List<QRcodeDateDTO> getOperatorQrcode(QRcodeCategory category);


    List<Coupon> getCouponLog();

    List<UserInfoDTO> getUserInfo();
}
