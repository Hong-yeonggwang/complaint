package com.yongin.complaint.Service.coupon.Impl;

import com.yongin.complaint.DAO.CouponDAO;
import com.yongin.complaint.DTO.QRcodeDTO;
import com.yongin.complaint.JPA.Entity.Member;
import com.yongin.complaint.Payload.response.CreateCouponResponse;
import com.yongin.complaint.JPA.Entity.Coupon;
import com.yongin.complaint.JPA.Entity.QRcodeCategory;
import com.yongin.complaint.Payload.response.QRcodeResponse;
import com.yongin.complaint.Component.QRcodeGenerater.QRcodeGenerater;
import com.yongin.complaint.Service.coupon.CouponService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Random;

@Service
public class CouponServiceImpl implements CouponService {

    private CouponDAO couponDAO;
    private QRcodeGenerater qrCodeGenerater;
    private final Logger LOGGER = LoggerFactory.getLogger(CouponServiceImpl.class);

    @Autowired
    public CouponServiceImpl(CouponDAO couponDAO, QRcodeGenerater qrCodeGenerater){
        this.couponDAO = couponDAO;
        this.qrCodeGenerater = qrCodeGenerater;
    }

    @Override
    public CreateCouponResponse createCoupon(String category, String name) throws NullPointerException{
        CouponGenerator generator = new CouponGenerator();
        String couponSerial = generator.generateRandomCoupon(); //쿠폰번호

        Coupon existCoupon = couponDAO.CouponExist(couponSerial); // 디비에 쿠폰번호가 존재하는지 확인

        if(existCoupon == null){
            LOGGER.info("[createCoupon] 중복되는 쿠폰 번호가 없습니다.");
            try{
                QRcodeCategory qrCategory = couponDAO.findByName(category, name);
                couponDAO.saveCoupon(couponSerial,qrCategory.getQrCategorySeq());
                return CreateCouponResponse.builder()
                        .msg("생성이 완료 되었습니다.")
                        .name(name)
                        .coupon(couponSerial)
                        .price(qrCategory.getPrice())
                        .build();
            }catch (NullPointerException e){
                return  CreateCouponResponse.builder()
                        .coupon("오류")
                        .msg("올바르지 않은 값입니다.")
                        .name(null)
                        .price(null)
                        .build();
            }

        }
        else{
            LOGGER.info("[createCoupon] 중복되는 쿠폰 번호가 존재합니다.");
            CreateCouponResponse returnDate = CreateCouponResponse.builder()
                    .coupon(null)
                    .msg("중복된 쿠폰 번호가 존재합니다. 다시 시도해주세요")
                    .name(null)
                    .price(null)
                    .build();
            return returnDate;
        }
    }

    @Override
    public QRcodeResponse useCoupon(String couponSerial, Member member) {
        try{
            Coupon coupon = couponDAO.CouponExist(couponSerial);
            if(coupon != null || coupon.getStatus().equals("none")){ //쿠폰이 존재하고 사용상태가 none이라면 사용가능
                LOGGER.info("[useCoupon]:쿠폰을 정상적으로 등록합니다.");
                QRcodeDTO data = qrCodeGenerater.generateQRcode(coupon.getQrCodeCategory(),couponSerial,member);
                couponDAO.updateCouponStatus(couponSerial); // 쿠폰의 사용유무를 use로 바꿈
                return QRcodeResponse.builder()
                        .qRcode(data.getQrSerial())
                        .msg("쿠폰이 정상적으로 사용됐습니다.")
                        .build();
            }else{
                LOGGER.info("[useCoupon]: 쿠폰인증 실패 에러 반환.");
                return QRcodeResponse.builder()
                        .qRcode("잘못된 입력값")
                        .msg("쿠폰번호를 확인해주세요")
                        .build();
            }
        }catch(NullPointerException e){
            LOGGER.info("[useCoupon]: 쿠폰인증 실패 에러 반환.");
            return QRcodeResponse.builder()
                    .qRcode("잘못된 입력값")
                    .msg("쿠폰번호를 확인해주세요")
                    .build();
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