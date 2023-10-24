package com.yongin.complaint.Controller;

import com.yongin.complaint.DTO.Admin.OperatorDTO;
import com.yongin.complaint.JPA.Entity.QRcodeCategory;
import com.yongin.complaint.Payload.response.Admin.CategoryDTO;
import com.yongin.complaint.Payload.response.Admin.CouponUseRateResponse;
import com.yongin.complaint.Payload.response.Admin.ServiceStatusResponse;
import com.yongin.complaint.Service.Admin.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
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
    public void addCategory(){

    }
    @GetMapping(value= "/category")
    public List<QRcodeCategory> getCategory(){
        return adminService.getCategoryList();
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
    public List<OperatorDTO> getOperatorStatis(){
        return adminService.getOperatorList();
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
