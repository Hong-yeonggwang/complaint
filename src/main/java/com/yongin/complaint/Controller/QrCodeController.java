package com.yongin.complaint.Controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/qrcode")
public class QrCodeController {

    @PostMapping(value = "/coupon") // 쿠폰 생성
    public void couponTicket(@RequestBody String hello){
        System.out.println(hello);
    }

    @PostMapping(value = "/purchase") //쿠폰 구매
    public void purchaseTicket(){

    }
    @DeleteMapping(value = "/coupon") // 쿠폰 사용
    public void useCoupon(){}

    @PutMapping // "/qrcode" qrcode 사용
    public void useQrCode(){
        System.out.println("asdas");
    }
}
