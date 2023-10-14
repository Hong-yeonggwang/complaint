package com.yongin.complaint.Controller;

import com.yongin.complaint.JPA.Entity.Member;
import com.yongin.complaint.Payload.requset.SignInRequest;
import com.yongin.complaint.Payload.requset.SignUpAdminRequest;
import com.yongin.complaint.Payload.requset.SignUpRequest;
import com.yongin.complaint.Payload.requset.UserInfoUpdateRequest;
import com.yongin.complaint.Payload.response.MemberInfoResponse;
import com.yongin.complaint.Payload.response.SignInResponse;
import com.yongin.complaint.Payload.response.SignUpResponse;
import com.yongin.complaint.Service.Admin.AdminService;
import com.yongin.complaint.Service.security.SignService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private final Logger LOGGER = LoggerFactory.getLogger(AdminController.class);
    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService){
        this.adminService = adminService;
    }


    @PostMapping(value = "/category")
    public void getCategory(){

    }

    @PutMapping(value = "/category")
    public void updateCategory(){
    }
    @DeleteMapping(value = "/category")
    public void deleteCategory(){}

    @PutMapping(value = "/admininfo")
    public void updateAdminInfo(){

    }
    @PutMapping(value = "/operaorInfo")
    public void updateOperatorInfo(){

    }
    @PostMapping(value = "/AqrcodeSatus")
    public void getAllQRcodeStatus(){ // 모든 qrcode의 사용량을 확인 ex(인성관 11/12)

    }
    @PostMapping(value = "/AcouponStatus")
    public void getAllCouponStatus(){ // 모든 qrcode의 사용량을 확인 ex(인성관 11/12)

    }


    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<Map<String,String>> ExceptionHandler(RuntimeException e){
        HttpHeaders responseHeaders = new HttpHeaders();
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        LOGGER.info("ExceptionHandler 호출.");
        LOGGER.info("원인: {}", e.toString());
        e.printStackTrace();

        Map<String,String> map = new HashMap<>();
        map.put("error type",httpStatus.getReasonPhrase());
        map.put("code","400");
        map.put("message", e.getMessage() != null ? e.getMessage().toString() : "에러 발생");
        return new ResponseEntity<>(map,responseHeaders,httpStatus);
    }
}
