package com.yongin.complaint.Coupon;

import com.yongin.complaint.DAO.CouponDAO;
import com.yongin.complaint.DTO.coupon.coupon.CreateCouponResponse;
import com.yongin.complaint.Service.coupon.CouponService;
import com.yongin.complaint.Service.coupon.Impl.CouponServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

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
}

