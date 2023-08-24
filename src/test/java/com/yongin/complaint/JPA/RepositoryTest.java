package com.yongin.complaint.JPA;

import com.yongin.complaint.JPA.Entity.Coupon;
import com.yongin.complaint.JPA.Entity.Member;
import com.yongin.complaint.JPA.Repository.CouponRepository;
import com.yongin.complaint.JPA.Repository.MemberRepository;
import com.yongin.complaint.JPA.Repository.QRcodeRepository;
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
    @Autowired
    private QRcodeRepository qRcodeRepository;

    @Test
    public void selectTest(){
        Coupon a = couponRepository.getBySerial("12312asdad");
        System.out.println(a.getQrCodeCategory().getPrice());
    }

    @Test
    public void TestQrcodeSelect(){
        System.out.println(qRcodeRepository.existsQRcode("11d4ddc357e0bfd226b6e1c2aac018d076a54da4f65e1dc8180684ac3"));
    }
}
