package com.yongin.complaint.JPA;

import com.yongin.complaint.JPA.Entity.Coupon;
import com.yongin.complaint.JPA.Entity.Member;
import com.yongin.complaint.JPA.Repository.CouponRepository;
import com.yongin.complaint.JPA.Repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class RepositoryTest {
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private CouponRepository couponRepository;

    @Test
    public void selectTest(){
        Coupon a = couponRepository.getBySerial("12312asdad");
        System.out.println(a.getQrCodeCategory().getPrice());
    }
}
