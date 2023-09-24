package com.yongin.complaint.Service.security.impl;

import com.yongin.complaint.Common.CommonResponse;
import com.yongin.complaint.Payload.response.SignInResponse;
import com.yongin.complaint.Payload.requset.SignUpRequest;
import com.yongin.complaint.Payload.response.SignUpResponse;
import com.yongin.complaint.JPA.Entity.Member;
import com.yongin.complaint.JPA.Repository.MemberRepository;
import com.yongin.complaint.Service.security.SignService;
import com.yongin.complaint.config.security.JwtProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class SignServiceimpl implements SignService {
    private final Logger LOGGER = LoggerFactory.getLogger(SignServiceimpl.class);

    public MemberRepository memberRepository;
    public JwtProvider jwtProvider;
    public PasswordEncoder passwordEncoder;

    @Autowired
    public SignServiceimpl(MemberRepository memberRepository, JwtProvider jwtProvider, PasswordEncoder passwordEncoder){
        this.memberRepository = memberRepository;
        this.jwtProvider = jwtProvider;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public SignUpResponse signUp(SignUpRequest signUpRequest) {
        LOGGER.info("[getSignUpResult] 회원 가입 정보 전달");
        Member member;
        member = Member.builder()
                .id(signUpRequest.getId())
                .name(signUpRequest.getName())
                .pwd(passwordEncoder.encode(signUpRequest.getPassword()))
                .roles((Collections.singletonList("ROLE_ADMIN")))
                .nickName(signUpRequest.getNickName())
                .birth(signUpRequest.getBirth())
                .major(signUpRequest.getMajor())
                .build();

        Member savedAdmin = memberRepository.save(member);
        SignUpResponse signUpResponse = new SignUpResponse();

        LOGGER.info("[getSignUpResult] adminEntity 값이 들어왔는지 확인 후 결과값 주입");
        if(!savedAdmin.getName().isEmpty()){
            LOGGER.info("[getSignUpResult] 정상 처리 완료 ");
            setSuccessResult(signUpResponse);
        }else{
            LOGGER.info("[getSignUpResult] 실패 처리 완료 ");
            setFailResult(signUpResponse);
        }
        return signUpResponse;
    }

    @Override
    public SignInResponse signIn(String id, String password) throws RuntimeException {
        LOGGER.info("[getSignInResult] signDataHandler로 회원 정보 요청");
        Member member = memberRepository.getById(id);
        LOGGER.info("[getSignInResult] id : {} ",id);
        LOGGER.info("[getSignInResult] 패스워드 비교 수행");

        if(!passwordEncoder.matches(password, member.getPassword())){
            throw new RuntimeException();
        }
        LOGGER.info("[getSignInResult] 패스워드 일치");

        LOGGER.info("[getSignInResult] signInResultDTO 객체 생성");
        SignInResponse signInResultDTO = SignInResponse.builder()
                .token(jwtProvider.createToken(String.valueOf(member.getId()),member.getRoles()))
                .build();

        LOGGER.info("[getSignInResult] SignInResultDto 객체에 값 주입");
        setSuccessResult(signInResultDTO);
        return signInResultDTO;
    }

    private void setSuccessResult(SignUpResponse result){
        result.setSuccess(true);
        result.setCode(CommonResponse.SUCCESS.getCode());
        result.setMsg(CommonResponse.SUCCESS.getMsg());
    }
    private void setFailResult(SignUpResponse result){
        result.setSuccess(true);
        result.setCode(CommonResponse.SUCCESS.getCode());
        result.setMsg(CommonResponse.SUCCESS.getMsg());
    }
}
