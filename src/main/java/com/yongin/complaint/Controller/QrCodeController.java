package com.yongin.complaint.Controller;

import com.yongin.complaint.Payload.requset.QRcodeRequest;
import com.yongin.complaint.Service.QRcode.QRcodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/qrcode")
public class QrCodeController {
    private QRcodeService QRcodeService;

    @Autowired
    public QrCodeController(QRcodeService QRcodeService){
        this.QRcodeService = QRcodeService;
    }

    @PostMapping(value = "/coupon") // 쿠폰 생성
    public void couponTicket(@RequestBody String hello){
        System.out.println(hello);
    }

    @PostMapping(value = "/purchase") //쿠폰 구매
    public void purchaseTicket(){

    }
    @DeleteMapping(value = "/coupon") // 쿠폰 사용
    public void useCoupon(){}

    @PostMapping// "/qrcode" qrcode 사용
    public String useQrCode(@RequestBody QRcodeRequest qrCode){
//        QRcodeService.useQrcode();
        System.out.println(qrCode.getQrCodeSerial());
        return qrCode.getQrCodeSerial();
    }
}
