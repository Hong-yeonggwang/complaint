package com.yongin.complaint.Service.Admin.Impl;

import com.yongin.complaint.DAO.AdminDAO;
import com.yongin.complaint.DTO.Admin.CategoryUpdateDTO;
import com.yongin.complaint.JPA.Entity.Coupon;
import com.yongin.complaint.JPA.Entity.QRcodeCategory;
import com.yongin.complaint.Payload.response.Admin.CouponUseRateResponse;
import com.yongin.complaint.Payload.response.Admin.ServiceStatusResponse;
import com.yongin.complaint.Service.Admin.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    private final AdminDAO adminDAO;

    @Autowired
    public AdminServiceImpl (AdminDAO adminDAO){
        this.adminDAO = adminDAO;
    }


    @Override
    public List<QRcodeCategory> getAllCategory() {
        return adminDAO.getAllCategory();
    }

    @Override
    public void updateCategory(String name, String placeName, CategoryUpdateDTO updateInfo){
        adminDAO.updateCategory(name,placeName,updateInfo);
    }

    @Override
    public void addCategory(CategoryUpdateDTO updateInfo) {
        adminDAO.addCategory(updateInfo);
    }

    @Override
    public void deleteCategory(String name) {

    }

    @Override
    public List<CouponUseRateResponse> getUseRateCoupon() {
        return adminDAO.getUseRateCoupon();
    }

    @Override
    public List<CouponUseRateResponse> getUseRateQRcode() {
        return adminDAO.getUseRateQRcode();
    }

    @Override
    @Transactional
    public ServiceStatusResponse getServiceStatus() {
        List<CouponUseRateResponse> couponUseRate = adminDAO.getUseRateQRcode(); // 쿠폰 사용비율 장소별 11/23
        List<CouponUseRateResponse> qrcodeUSeRate = adminDAO.getUseRateCoupon(); // qrcode 사용 비율 장소별 11/23

        Long userCount = adminDAO.getUserCount();
        Long placeCount = adminDAO.getPlaceCount();

        Long todayUsedCount = adminDAO.getTodayUsedQRcode(LocalDate.now().toString());
        Long todayCreatedCount = adminDAO.getTodayCreatedQRcode(LocalDate.now().toString());
        Long remainQrcodeCount = adminDAO.getRemainQrcodeCount();

        List<QRcodeCategory> categoryInfo = adminDAO.getAllCategory();

        List<Coupon> couponList = adminDAO.getCouponList();

        return ServiceStatusResponse.builder()
                .couponUseRate(couponUseRate)
                .qrcodeUSeRate(qrcodeUSeRate)
                .userCount(userCount)
                .placeCount(placeCount)
                .todayUsedCount(todayUsedCount)
                .todayCreatedCount(todayCreatedCount)
                .remainQrcodeCount(remainQrcodeCount)
                .categoryInfo(categoryInfo)
                .couponList(couponList)
                .build();
    }

}
