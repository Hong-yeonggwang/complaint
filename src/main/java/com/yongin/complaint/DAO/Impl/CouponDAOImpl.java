package com.yongin.complaint.DAO.Impl;

import com.yongin.complaint.DAO.CouponDAO;
import com.yongin.complaint.JPA.Entity.Coupon;
import com.yongin.complaint.JPA.Entity.QRcodeCategory;
import com.yongin.complaint.JPA.Repository.CouponRepository;
import com.yongin.complaint.JPA.Repository.QRcodeCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

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
    public QRcodeCategory findByName(String category, String name) {
        return qrCodeCategoryRepository.findByName(category,name);
    }

    @Override
    public void saveCoupon(String serial, Long category) {
        QRcodeCategory qRcodeCategory = new QRcodeCategory();
        qRcodeCategory.setQrCategorySeq(category);
        Coupon coupon = Coupon.builder()
                .serial(serial)
                .qrCodeCategory(qRcodeCategory)
                .status("none")
                .build();
        couponRepository.save(coupon);
    }

    @Transactional
    @Override
    public void updateCouponStatus(String couponSerial , Long memberSeq) {
        Coupon coupon = couponRepository.getBySerial(couponSerial);
        coupon.setStatus("use");
        coupon.setUserMemberSeq(memberSeq);
        couponRepository.save(coupon);
    }
}
