package com.yongin.complaint.Controller;

import com.yongin.complaint.JPA.Entity.Member;
import com.yongin.complaint.Payload.requset.SignInRequest;
import com.yongin.complaint.Payload.requset.SignUpAdminRequest;
import com.yongin.complaint.Payload.requset.UserInfoUpdateRequest;
import com.yongin.complaint.Payload.response.MemberInfoResponse;
import com.yongin.complaint.Payload.response.SignInResponse;
import com.yongin.complaint.Payload.requset.SignUpRequest;
import com.yongin.complaint.Payload.response.SignUpResponse;
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

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);
    private final SignService signService;

    @Autowired
    public AuthController(SignService signService){
        this.signService = signService;
    }

    @PostMapping(value = "/sign-in")
    public ResponseEntity signIn(@RequestBody SignInRequest signInRequest){
        LOGGER.info("[signIn] 로그인을 시도하고 있습니다.");
        SignInResponse signInResultDTO = signService.signIn(signInRequest.getId(), signInRequest.getPassword());
            if(signInResultDTO.getCode() == 0){
                LOGGER.info("[signIn] 정상적으로 로그인되었습니다.");
            }
            return ResponseEntity.ok(signInResultDTO);
    }
    @PostMapping(value = "/sign-up")
    public SignUpResponse signUp(@Validated @RequestBody SignUpRequest signUpRequest){
        LOGGER.info("[signUp] 회원가입을 수행합니다.");
        SignUpResponse signUpResponse = signService.signUp(signUpRequest);
        LOGGER.info("[signUp] 회원가입 완료.");
        return signUpResponse;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(value = "/sign-up/admin")
    public SignUpResponse signUpAdmin(@Validated @RequestBody SignUpAdminRequest signUpAdminRequest){
        LOGGER.info("[signUpAdmin] 관리자 아이디를 생성을 시작합니다.");
        SignUpResponse signUpResponse = signService.signUpAdmin(signUpAdminRequest);
        LOGGER.info("[signUpAdmin] 회원가입 완료.");
        return signUpResponse;
    }

    @Transactional
    @PostMapping(value = "/memeberinfo")
    public MemberInfoResponse getMemberInfo(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Member member = (Member)auth.getPrincipal();

        MemberInfoResponse info = MemberInfoResponse.builder()
                .name(member.getName())
                .birth(member.getBirth())
                .major(member.getMajor())
                .nickName(member.getNickName())
                .phoneNumber(member.getPhoneNumber())
                .build();

        return info;
    }
    @PutMapping(value = "/memberinfo")
    @Transactional
    public void updateUserInfo(@Validated @RequestBody UserInfoUpdateRequest userInfo){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Member member = (Member)auth.getPrincipal();

        signService.updateUserInfo(member,userInfo);
    }

    @GetMapping(value = "/exception")
    public void exceptionTest() throws RuntimeException{
        throw new RuntimeException("접근이 금지되었습니다.");
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
