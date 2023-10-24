package com.yongin.complaint.Service.Admin;

import com.yongin.complaint.DTO.Admin.CategoryUpdateDTO;
import com.yongin.complaint.DTO.Admin.OperatorDTO;
import com.yongin.complaint.JPA.Entity.QRcodeCategory;
import com.yongin.complaint.Payload.response.Admin.CategoryDTO;
import com.yongin.complaint.Payload.response.Admin.CouponUseRateResponse;
import com.yongin.complaint.Payload.response.Admin.ServiceStatusResponse;

import java.util.List;

public interface AdminService {

    List<QRcodeCategory> getAllCategory();

    void updateCategory(String name, String placeName, CategoryUpdateDTO updateInfo);

    void addCategory(CategoryUpdateDTO updateInfo);

    void deleteCategory(String name);

    List<CouponUseRateResponse> getUseRateCoupon();

    List<CouponUseRateResponse> getUseRateQRcode();

    ServiceStatusResponse getServiceStatus();

    List<OperatorDTO> getOperatorList();
    List<QRcodeCategory> getCategoryList();

}
