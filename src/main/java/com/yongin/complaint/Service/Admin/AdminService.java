package com.yongin.complaint.Service.Admin;

import com.yongin.complaint.DTO.Admin.*;
import com.yongin.complaint.JPA.Entity.Coupon;
import com.yongin.complaint.JPA.Entity.QRcodeCategory;
import com.yongin.complaint.Payload.response.Admin.CouponUseRateResponse;
import com.yongin.complaint.Payload.response.Admin.ServiceStatusResponse;

import java.util.List;

public interface AdminService {

    List<QRcodeCategory> getAllCategory();

    void deleteCategory(String name);

    List<CouponUseRateResponse> getUseRateCoupon();

    List<CouponUseRateResponse> getUseRateQRcode();

    ServiceStatusResponse getServiceStatus();

    List<OperatorDTO> getOperatorList();

    CategoryPlaceDTO getCategoryList();

    String addPlcae(String name);

    String addCategory(CategoryUpdateDTO updateInfo);

    String updateCategory(CategoryUpdateDTO categoryUpdateDTO);

    String updatePlace(Long seq, String name);

    List<QRcodeDateDTO> getOperatorQrcode(QRcodeCategory category);

    List<Coupon> getCouponLog();

    List<UserInfoDTO> getUserInfo();
}
