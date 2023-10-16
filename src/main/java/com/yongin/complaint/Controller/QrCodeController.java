package com.yongin.complaint.Controller;

import com.yongin.complaint.JPA.Entity.Member;
import com.yongin.complaint.JPA.Entity.QRcode;
import com.yongin.complaint.Payload.requset.CouponCreateRequest;
import com.yongin.complaint.Payload.response.CreateCouponResponse;
import com.yongin.complaint.Payload.response.QRcodeResponse;
import com.yongin.complaint.Service.QRcode.QRcodeService;
import com.yongin.complaint.Service.coupon.CouponService;
import com.yongin.complaint.Service.security.SignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/qrcode")
public class QrCodeController {
    private QRcodeService qrcodeService;
    private CouponService couponService;
    private SignService signService;

    @Autowired
    public QrCodeController(QRcodeService qrcodeService, CouponService couponService,SignService signService){
        this.qrcodeService = qrcodeService;
        this.couponService = couponService;
        this.signService = signService;
    }

    @PostMapping(value = "/coupon") // 쿠폰 생성
    public CreateCouponResponse couponTicket(@RequestBody CouponCreateRequest couponCreateRequest){
        return couponService.createCoupon(couponCreateRequest.getCategory(),couponCreateRequest.getName());
    }

    @PostMapping(value = "/purchase") //티켓 구매 qrcode 생성
    public void purchaseTicket(){

    }
    @PostMapping(value = "")
    public List<QRcode> getQRcodeList(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Member member = (Member)auth.getPrincipal();

        return qrcodeService.getQRcodeList(member.getId());
    }
    @DeleteMapping(value = "/coupon") // 쿠폰 사용
    public QRcodeResponse useCoupon(HttpServletRequest servletRequest, String couponSerial){
        Member member = signService.getMemberinfo(servletRequest);
        return couponService.useCoupon(couponSerial,member);
    }

    @DeleteMapping// "/qrcode" qrcode 사용
    public QRcodeResponse useQrCode(String qrCodeSerial){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Member member = (Member)auth.getPrincipal();

        return qrcodeService.useQrcode(qrCodeSerial,member);
    }
}
