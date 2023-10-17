package com.yongin.complaint.Service.Admin.Impl;

import com.yongin.complaint.DAO.AdminDAO;
import com.yongin.complaint.DTO.Admin.CategoryUpdateDTO;
import com.yongin.complaint.JPA.Entity.QRcodeCategory;
import com.yongin.complaint.Payload.response.Admin.CouponUseRateResponse;
import com.yongin.complaint.Service.Admin.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
