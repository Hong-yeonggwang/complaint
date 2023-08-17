package com.yongin.complaint.Controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/qrcode")
public class QrCodeController {

    @PostMapping(value = "/coupon")
    public void couponTicket(@RequestBody String hello){
        System.out.println(hello);
    }

    @PostMapping(value = "/purchase")
    public void purchaseTicket(){

    }
}
