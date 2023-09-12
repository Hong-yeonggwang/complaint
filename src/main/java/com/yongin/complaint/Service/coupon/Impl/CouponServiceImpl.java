package com.yongin.complaint.Service.coupon.Impl;

import com.yongin.complaint.DAO.CouponDAO;
import com.yongin.complaint.Payload.response.CreateCouponResponse;
import com.yongin.complaint.JPA.Entity.Coupon;
import com.yongin.complaint.JPA.Entity.QRcodeCategory;
import com.yongin.complaint.Service.coupon.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Random;

@Service
public class CouponServiceImpl implements CouponService {

    private CouponDAO couponDAO;

    @Autowired
    public CouponServiceImpl(CouponDAO couponDAO){
        this.couponDAO = couponDAO;
    }

    @Override
    public CreateCouponResponse createCoupon(String category, String name){
        CouponGenerator generator = new CouponGenerator();
        String couponSerial = generator.generateRandomCoupon(); //쿠폰번호

        Coupon existCoupon = couponDAO.CouponExist(couponSerial); // 디비에 쿠폰번호가 존재하는지 확인

        if(existCoupon == null){
            QRcodeCategory priceData = couponDAO.getPrice(category, name);
            return CreateCouponResponse.builder()
                    .msg("생성이 완료 되었습니다.")
                    .name(name)
                    .coupon(couponSerial)
                    .price(priceData.getPrice())
                    .build();
        }
        else{
            CreateCouponResponse returnDate = CreateCouponResponse.builder()
                    .coupon(null)
                    .msg("중복된 쿠폰 번호가 존재합니다.")
                    .name(null)
                    .price(null)
                    .build();
            return returnDate;
        }
    }

    public static class CouponGenerator {
        private final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        private final int COUPON_LENGTH = 10; // 쿠폰 번호 길이 설정

        public String generateRandomCoupon() {
            StringBuilder sb = new StringBuilder(COUPON_LENGTH);
            Random random = new SecureRandom();

            for (int i = 0; i < COUPON_LENGTH; i++) {
                int randomIndex = random.nextInt(ALPHABET.length());
                sb.append(ALPHABET.charAt(randomIndex));
            }

            return sb.toString();
        }

    }
}