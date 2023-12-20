package com.yongin.complaint.Controller;

import com.yongin.complaint.DTO.Member.QRcodeLogDTO;
import com.yongin.complaint.JPA.Entity.Member;
import com.yongin.complaint.JPA.Entity.QRcode;
import com.yongin.complaint.JPA.Entity.QRcodeCategory;
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
@RequestMapping("/v1/qrcode")
public class QrCodeController {
    private final QRcodeService qrcodeService;
    private final CouponService couponService;
    private final SignService signService;

    @Autowired
    public QrCodeController(QRcodeService qrcodeService, CouponService couponService, SignService signService) {
        this.qrcodeService = qrcodeService;
        this.couponService = couponService;
        this.signService = signService;
    }

    @PostMapping(value = "/coupon") // 쿠폰 생성
    public CreateCouponResponse couponTicket(@RequestBody CouponCreateRequest couponCreateRequest) {
        return couponService.createCoupon(couponCreateRequest.getCategory(), couponCreateRequest.getName());
    }

    @PostMapping(value = "/purchase") //티켓 구매 qrcode 생성
    public void purchaseTicket() {

    }

    @PostMapping(value = "")
    public List<QRcode> getQRcodeList() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Member member = (Member) auth.getPrincipal();

        return qrcodeService.getQRcodeList(member.getId());
    }

    @DeleteMapping(value = "/coupon") // 쿠폰 사용
    public QRcodeResponse useCoupon(HttpServletRequest servletRequest, String couponSerial) {
        Member member = signService.getMemberinfo(servletRequest);
        return couponService.useCoupon(couponSerial, member);
    }

    @DeleteMapping// "/qrcode" qrcode 사용
    public QRcodeResponse useQrCode(String qrCodeSerial) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Member member = (Member) auth.getPrincipal();

        QRcodeCategory qRcodeCategory = signService.getOperatorInfo(member.getMemberSeq());

        return qrcodeService.useQrcode(qrCodeSerial, qRcodeCategory.getQrCategorySeq());
    }

    @GetMapping(value = "/log")
    public List<QRcodeLogDTO> getQRcodeLog() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Member member = (Member) auth.getPrincipal();

        return qrcodeService.getQRcodeLog(member.getMemberSeq());
    }
}
