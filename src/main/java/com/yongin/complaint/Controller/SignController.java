package com.yongin.complaint.Controller;

import com.yongin.complaint.Payload.requset.SignInRequest;
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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
//@CrossOrigin(origins = "http://localhost:8085")
public class SignController {
    private final Logger LOGGER = LoggerFactory.getLogger(SignController.class);
    private final SignService signService;

    @Autowired
    public SignController(SignService signService){
        this.signService = signService;
    }

    @PostMapping(value = "/sign-in")
    public SignInResponse signIn(@RequestBody SignInRequest signInRequest)throws RuntimeException{
        LOGGER.info("[signIn] 로그인을 시도하고 있습니다.");

        SignInResponse signInResultDTO = signService.signIn(signInRequest.getId(), signInRequest.getPassword());

        if(signInResultDTO.getCode() == 0){
            LOGGER.info("[signIn] 정상적으로 로그인되었습니다.");
        }
        return signInResultDTO;
    }
    @PostMapping(value = "/sign-up")
    public SignUpResponse signUp(@Validated @RequestBody SignUpRequest signUpRequest){
        LOGGER.info("[signUp] 회원가입을 수행합니다.");
        SignUpResponse signUpResponse = signService.signUp(signUpRequest);
        LOGGER.info("[signUp] 회원가입 완료.");
        return signUpResponse;
    }
    @GetMapping(value = "/exception")
    public void exceptionTest() throws RuntimeException{
        throw new RuntimeException("접근이 금지되었습니다.");
    }

    @PostMapping(value = "")
    public Boolean checkToken(){
        LOGGER.info("[checkToken]: 사용자가 토큰 검증만을 요구하고 있습니다.");
        return true;
    }

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<Map<String,String>> ExceptionHandler(RuntimeException e){
        HttpHeaders responseHeaders = new HttpHeaders();
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        LOGGER.info("ExceptionHandler 호출.");
        LOGGER.info(e.getCause().toString());
        LOGGER.info(e.getMessage().toString());

        Map<String,String> map = new HashMap<>();
        map.put("error type",httpStatus.getReasonPhrase());
        map.put("code","400");
        map.put("message","에러 발생");
        return new ResponseEntity<>(map,responseHeaders,httpStatus);
    }
}
