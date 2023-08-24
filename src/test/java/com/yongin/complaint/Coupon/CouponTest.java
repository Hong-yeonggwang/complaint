package com.yongin.complaint.Coupon;

import com.yongin.complaint.DTO.coupon.CreateCouponResponse;
import com.yongin.complaint.Service.coupon.CouponService;
import com.yongin.complaint.Service.coupon.Impl.CouponServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@SpringBootTest
public class CouponTest{

//    @Mock
//    private CouponDAO couponDAO; // Mocked CouponDAO
//
//    @InjectMocks
//    private CouponService couponService = new CouponServiceImpl(couponDAO);

    @Autowired
    private CouponService couponService;

    @Test
    public void testCreateCoupon() {
        String name = "강남행 등교";
        String category = "버스";

        // CouponService의 createCoupon 메서드 호출
        CreateCouponResponse response = couponService.createCoupon(category,name);

        System.out.println(response.getCoupon());

        // 테스트 검증
//        assertNotNull(response);
//        assertEquals("생성이 완료 되었습니다.", response.getMsg());
//        assertEquals(name, response.getName());
//        assertNotNull(response.getCoupon());
//        assertEquals(1200, response.getPrice());
    }

    @Test
    public void testCoupon(){
        CouponServiceImpl.CouponGenerator test = new CouponServiceImpl.CouponGenerator();
        test.generateRandomCoupon();

    }

    @Test
    public void test(){
        String input = "helloWorld";
        try {
            MessageDigest sha256Digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = sha256Digest.digest(input.getBytes());

            // Convert the byte array to a hexadecimal string
            String hashHex = DatatypeConverter.printHexBinary(hashBytes).toLowerCase();

            System.out.println(hashHex);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}

