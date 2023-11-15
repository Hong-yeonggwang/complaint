package com.yongin.complaint.Controller;

import com.yongin.complaint.DTO.Admin.*;
import com.yongin.complaint.JPA.Entity.Coupon;
import com.yongin.complaint.JPA.Entity.Member;
import com.yongin.complaint.JPA.Entity.QRcodeCategory;
import com.yongin.complaint.Payload.response.Admin.CategoryDTO;
import com.yongin.complaint.Payload.response.Admin.CouponUseRateResponse;
import com.yongin.complaint.Payload.response.Admin.ServiceStatusResponse;
import com.yongin.complaint.Service.Admin.AdminService;
import com.yongin.complaint.Service.security.SignService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/admin")
public class AdminController {
    private final Logger LOGGER = LoggerFactory.getLogger(AdminController.class);
    private final AdminService adminService;
    private final SignService signService;

    @Autowired
    public AdminController(AdminService adminService,SignService signService){
        this.adminService = adminService;
        this.signService = signService;
    }
    @GetMapping(value= "/category")
    public CategoryPlaceDTO getCategory(){
        return adminService.getCategoryList();
    }
    @PostMapping(value = "/place")
    public String updatePlace(@RequestBody CategoryUpdateDTO params){
        String name = params.getName();
        Long seq = params.getCategorySeq();
        return adminService.updatePlace(seq,name);
    }
    @PutMapping(value = "/place")
    public String addPlace(@RequestBody Map<String,String> params){
        String name = params.get("name");
        return adminService.addPlcae(name);
    }
    @DeleteMapping(value = "/place")
    public void deletePlace(){}

    @PostMapping(value = "/category")
    public String updateCategory(@RequestBody CategoryUpdateDTO params){
        return adminService.updateCategory(params);

    }
    @PutMapping(value = "/category")
    public String addCategory(@RequestBody CategoryUpdateDTO params){
        return adminService.addCategory(params);
    }

    @PutMapping(value = "/admininfo")
    public void updateAdminInfo(){

    }
    @PutMapping(value = "/operaorInfo")
    public void updateOperatorInfo(){

    }
    @PostMapping(value = "/AqrcodeSatus")
    public List<CouponUseRateResponse> getAllQRcodeStatus(){ // 모든 qrcode의 사용량을 확인 ex(인성관 11/12)
        return adminService.getUseRateQRcode();
    }
    @PostMapping(value = "/AcouponStatus")
    public List<CouponUseRateResponse> getAllCouponStatus(){ // 모든 qrcode의 사용량을 확인 ex(인성관 11/12)
        return adminService.getUseRateCoupon();
    }

    @GetMapping(value = "/serviceStatus")
    public ServiceStatusResponse getServiceStatus(){
        return adminService.getServiceStatus();
    }

    @GetMapping(value = "/operatorStatus")
    public List<OperatorDTO> getOperatorStatus(){
        return adminService.getOperatorList();
    }

    @GetMapping(value = "/operator/qrcode")
    public List<QRcodeDateDTO> getOperatorQrcode(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Member member = (Member) auth.getPrincipal();
        QRcodeCategory category = signService.getOperatorInfo(member.getMemberSeq());
        return adminService.getOperatorQrcode(category);
    }

    @GetMapping(value = "/coupon/log")
    public List<Coupon> getCouponLog(){
        return adminService.getCouponLog();
    }

    @GetMapping(value = "/user/log")
    public List<UserInfoDTO> getUserInfoLog(){
        return adminService.getUserInfo();
    }



}
