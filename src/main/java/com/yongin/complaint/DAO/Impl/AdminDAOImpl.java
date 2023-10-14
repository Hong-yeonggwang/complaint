package com.yongin.complaint.DAO.Impl;

import com.yongin.complaint.DAO.AdminDAO;
import com.yongin.complaint.JPA.Repository.CouponRepository;
import com.yongin.complaint.JPA.Repository.MemberRepository;
import com.yongin.complaint.JPA.Repository.QRcodeCategoryRepository;
import com.yongin.complaint.JPA.Repository.QRcodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AdminDAOImpl implements AdminDAO {
    private final MemberRepository memberRepository;
    private final CouponRepository couponRepository;
    private final QRcodeCategoryRepository qrCodeCategoryRepository;
    private final QRcodeRepository qrCodeRepository;

    @Autowired
    public AdminDAOImpl(MemberRepository memberRepository,
                        CouponRepository couponRepository,
                        QRcodeCategoryRepository qrCodeCategoryRepository,
                        QRcodeRepository qrCodeRepository){
        this.memberRepository = memberRepository;
        this.couponRepository = couponRepository;
        this.qrCodeRepository = qrCodeRepository;
        this.qrCodeCategoryRepository = qrCodeCategoryRepository;

    }

}
