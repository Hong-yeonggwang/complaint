package com.yongin.complaint.JPA;

import com.yongin.complaint.Common.MemberRoleEnum;
import com.yongin.complaint.DAO.QRcodeDAO;
import com.yongin.complaint.JPA.Entity.Coupon;
import com.yongin.complaint.JPA.Entity.MemberRole;
import com.yongin.complaint.JPA.Entity.QRcode;
import com.yongin.complaint.JPA.Repository.*;
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
    @Autowired
    private QRcodeCategoryRepository qrCodeCategoryRepository;
    @Autowired
    private MemberRoleRepository memberRoleRepository;

    @Autowired
    private QRcodeDAO qrCodeDAO;

    @Test
    public void selectTest(){
        Coupon a = couponRepository.getBySerial("12312asdad");
        a.setSerial("123");
        couponRepository.save(a);
    }

    @Test
    public void TestQrcodeSelect(){
        System.out.println(qRcodeRepository.existsQRcode("11d4ddc357e0bfd226b6e1c2aac018d076a54da4f65e1dc8180684ac3"));
    }

    @Test
    public void testQrcodeUseDateNull(){
        System.out.println(qRcodeRepository.existsQRcode("11d4ddc357e0822968dbfd226b6e1c2aac018d076a54da4f65e1dc8180684ac3").getUseDate());
    }

    @Test
    public void testGetById(){
        System.out.println(memberRepository.getById("123").getId());
    }

    @Test
    public void testGetByName(){
        System.out.println(qrCodeCategoryRepository.getByQrCodeCategory("버스").getName());
    }

    @Test
    public void testQRcodeList(){
        List<QRcode> qRcodeList = qRcodeRepository.getQrcodeList("a");
        qRcodeList.forEach(test -> System.out.println(test.toString()));
    }
    @Test
    public void testEnumColum(){
        MemberRole memberRole = MemberRole.builder()
                .role(MemberRoleEnum.valueOf("ROLE_USER")).build();
        System.out.println(memberRole.getRole());
        memberRoleRepository.save(memberRole);
    }
}
