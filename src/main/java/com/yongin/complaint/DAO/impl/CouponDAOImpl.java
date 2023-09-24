package com.yongin.complaint.DAO.impl;

import com.yongin.complaint.DAO.CouponDAO;
import com.yongin.complaint.JPA.Entity.Coupon;
import com.yongin.complaint.JPA.Entity.QRcodeCategory;
import com.yongin.complaint.JPA.Repository.CouponRepository;
import com.yongin.complaint.JPA.Repository.QRcodeCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CouponDAOImpl implements CouponDAO {
    private CouponRepository couponRepository;
    private QRcodeCategoryRepository qrCodeCategoryRepository;

    @Autowired
    public CouponDAOImpl(
            CouponRepository couponRepository,
            QRcodeCategoryRepository qrCodeCategoryRepository
    ){
        this.couponRepository = couponRepository;
        this.qrCodeCategoryRepository = qrCodeCategoryRepository;
    }

    @Override
    public Coupon CouponExist(String serial){
        Coupon coupon = couponRepository.getBySerial(serial);
        return coupon;
    }

    @Override
    public QRcodeCategory getPrice(String category, String name) {
        return qrCodeCategoryRepository.getPrice(name,category);
    }
}
