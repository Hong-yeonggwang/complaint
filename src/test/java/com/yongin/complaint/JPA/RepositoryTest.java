package com.yongin.complaint.JPA;

import com.yongin.complaint.DAO.AdminDAO;
import com.yongin.complaint.DAO.ChatRoomDAO;
import com.yongin.complaint.DTO.Admin.CouponStatisticsDTO;
import com.yongin.complaint.DTO.Admin.QRcodeDateDTO;
import com.yongin.complaint.DTO.ChatRoomInfoDTO;
import com.yongin.complaint.DTO.ChatRoomMemberDTO;
import com.yongin.complaint.JPA.Entity.*;
import com.yongin.complaint.JPA.Repository.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
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
    private ChatRoomInfoRepository chatRoomInfoRepository;

    @Autowired
    private ChatRoomDAO chatDAO;

    @Autowired
    private AdminDAO adminDAO;

    @Autowired
    private OperatorRepository operatorRepository;

//
//    @Test
//    public void TestQrcodeSelect(){
//        System.out.println(qRcodeRepository.existsQRcode("11d4ddc357e0bfd226b6e1c2aac018d076a54da4f65e1dc8180684ac3"));
//    }
//
//    @Test
//    public void testQrcodeUseDateNull(){
//        System.out.println(qRcodeRepository.existsQRcode("11d4ddc357e0822968dbfd226b6e1c2aac018d076a54da4f65e1dc8180684ac3").getUseDate());
//    }
//
//    @Test
//    public void testGetById(){
//        System.out.println(memberRepository.getById("123").getId());
//    }
//
//    @Test
//    public void testQRcodeList(){
//        List<QRcode> qRcodeList = qRcodeRepository.getQrcodeList("q");
//        qRcodeList.forEach(test -> System.out.println(test.toString()));
//    }
//    @Test
//    public void testDtoMapping(){
//        ChatRoomInfoDTO test = chatRoomInfoRepository.getChatRoomInfoDTOByChatRoomId("123");
//        System.out.println(test.toString());
//
//    }
//    @Test
//    public void test(){
//        List<ChatRoomMemberDTO> test = chatRoomInfoRepository.getChatRoomMemberDTOListByChatRoomId("123");
//        test.forEach(name -> System.out.println(name.toString()));
//    }
//
////    @Test
////    public void chatDAOtest(){
////        System.out.println(chatDAO.findChatRoomsWithMembers("123").toString());
////    }
//
//    @Test
//    @Transactional
//    public void testGetByOwner(){
//        Member memberSeq = new Member();
//        memberSeq.setMemberSeq(14L);
//        List<QRcode> data = qRcodeRepository.getByOwner(memberSeq);
//        data.forEach(name->System.out.println(name.toString()));
//    }
//
//    @Test
//    public void testgetQrcodeList(){
//        List<QRcode> data = qRcodeRepository.getQrcodeList("q");
//        data.forEach(name->System.out.println(name.toString()));
//    }
//
//    @Test
//    @Rollback(false)
//    public void testSaveLogic(){
//        List<QRcodeCategory> test = qrCodeCategoryRepository.findAll();
//
//        test.forEach(name->System.out.println(name.toString()));
//    }
//
//    @Test
//    public void testGroupBY(){
//        List<CouponStatisticsDTO> data = couponRepository.getCouponCount();
//        data.forEach(name->System.out.println(name.toString()));
//    }
//
//    @Test
//    public void getUseRate(){
//        List<CouponStatisticsDTO> data = couponRepository.getCouponCount();
//        data.forEach(name->System.out.println(name.toString()));
//
//        List<CouponStatisticsDTO> data1 = couponRepository.getCouponUseCount();
//        data1.forEach(name->System.out.println(name.toString()));
//        adminDAO.getUseRateCoupon();
//    }
//
//    @Test
//    public void getQrcodeUseRate(){
//        List<CouponStatisticsDTO> data = couponRepository.getCouponCount();
//        data.forEach(name->System.out.println(name.toString()));
//
//        List<CouponStatisticsDTO> data1 = couponRepository.getCouponUseCount();
//        data1.forEach(name->System.out.println(name.toString()));
//        adminDAO.getUseRateCoupon().forEach(name->System.out.println(name.toString()));
//
//    }
//
////    @Test
////    public void selectChatRoom(){
////        System.out.println(chatDAO.findChatRoomsWithMembers("123"));
////
////        ChatRoomInfo chatRoomInfo =  new ChatRoomInfo();
////        chatRoomInfo.setChatRoomSeq(2L);
////        Member member = new Member();
////        member.setMemberSeq(13l);
////        chatRoomInfo.setMembers(List.of(member));
////
////        chatRoomInfoRepository.save(chatRoomInfo);
////    }
//    @Test
//    public void test12(){
//        System.out.println(LocalDate.of(2021,1,14).toString());
//        System.out.println(LocalDate.now().toString());
//        System.out.println(qRcodeRepository.getTodayCreatedQrcodeCount(LocalDate.now().toString()));
//    }

//    @Test
//    @Transactional
//    public void test(){
//        operatorRepository.getOperator().forEach(name -> System.out.println(name.toString()));
//    }
//    @Test
//    @Transactional
//    public void test1(){
//        QRcodeCategory adminQRcodeSeq = qrCodeCategoryRepository.findByName("택시");
//        System.out.println(adminQRcodeSeq.toString());
//    }
}
