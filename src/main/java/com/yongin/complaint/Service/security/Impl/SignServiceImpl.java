package com.yongin.complaint.Service.security.Impl;

import com.yongin.complaint.Common.CommonResponse;
import com.yongin.complaint.Common.MemberRoleEnum;
import com.yongin.complaint.Component.QRcodeGenerater.QRcodeGenerater;
import com.yongin.complaint.Component.emailSender.EmailSender;
import com.yongin.complaint.DTO.Email.EmailDTO;
import com.yongin.complaint.JPA.Entity.Member;
import com.yongin.complaint.JPA.Entity.Operator;
import com.yongin.complaint.JPA.Entity.PasswordCode;
import com.yongin.complaint.JPA.Entity.QRcodeCategory;
import com.yongin.complaint.JPA.Repository.MemberRepository;
import com.yongin.complaint.JPA.Repository.OperatorRepository;
import com.yongin.complaint.JPA.Repository.PasswordCodeRepository;
import com.yongin.complaint.JPA.Repository.QRcodeCategoryRepository;
import com.yongin.complaint.Payload.requset.SignUpAdminRequest;
import com.yongin.complaint.Payload.requset.SignUpRequest;
import com.yongin.complaint.Payload.requset.UserInfoUpdateRequest;
import com.yongin.complaint.Payload.response.SignInResponse;
import com.yongin.complaint.Payload.response.SignUpResponse;
import com.yongin.complaint.Service.security.SignService;
import com.yongin.complaint.config.security.JwtProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

@Service
public class SignServiceImpl implements SignService {
    private final Logger LOGGER = LoggerFactory.getLogger(SignServiceImpl.class);
    public JwtProvider jwtProvider;
    public PasswordEncoder passwordEncoder;
    public QRcodeGenerater qrCodeGenerater;
    private final MemberRepository memberRepository;
    private final OperatorRepository operatorRepository;

    private final QRcodeCategoryRepository qrCodeCategoryRepository;
    private final PasswordCodeRepository passwordCodeRepository;

    private final EmailSender emailSender;


    @Autowired
    public SignServiceImpl(MemberRepository memberRepository, JwtProvider jwtProvider, PasswordEncoder passwordEncoder,
                           OperatorRepository operatorRepository, QRcodeCategoryRepository qrCodeCategoryRepository,
                           EmailSender emailSender, PasswordCodeRepository passwordCodeRepository,
                           QRcodeGenerater qrCodeGenerater
    ) {
        this.memberRepository = memberRepository;
        this.jwtProvider = jwtProvider;
        this.passwordEncoder = passwordEncoder;
        this.operatorRepository = operatorRepository;
        this.qrCodeCategoryRepository = qrCodeCategoryRepository;
        this.emailSender = emailSender;
        this.passwordCodeRepository = passwordCodeRepository;
        this.qrCodeGenerater = qrCodeGenerater;
    }

    @Override
    public SignUpResponse signUp(SignUpRequest signUpRequest) {
        LOGGER.info("[getSignUpResult] 회원 가입 정보 전달");
        Member member;
        member = Member.builder()
                .id(signUpRequest.getId())
                .name(signUpRequest.getName())
                .pwd(passwordEncoder.encode(signUpRequest.getPassword()))
                .role(MemberRoleEnum.valueOf("ROLE_USER"))
                .phoneNumber(signUpRequest.getPhoneNumber())
                .nickName(signUpRequest.getNickName())
                .birth(signUpRequest.getBirth())
                .major(signUpRequest.getMajor())
                .email(signUpRequest.getEmail())
                .build();

        Member savedAdmin = memberRepository.save(member);
//        이벤트 회원가입시 모든 사람들이게 간식 qr코드 주기
        QRcodeCategory qRcodeCategory = new QRcodeCategory();
        qRcodeCategory.setQrCategorySeq(16L);
        qrCodeGenerater.generateQRcode(qRcodeCategory, "SJRL24OS98S", member);


        SignUpResponse signUpResponse = new SignUpResponse();

        LOGGER.info("[getSignUpResult] Entity 값이 들어왔는지 확인 후 결과값 주입");
        if (!savedAdmin.getName().isEmpty()) {
            LOGGER.info("[getSignUpResult] 정상 처리 완료 ");
            setSuccessResult(signUpResponse);
        } else {
            LOGGER.info("[getSignUpResult] 실패 처리 완료 ");
            setFailResult(signUpResponse);
        }
        return signUpResponse;
    }

    @Override
    public SignInResponse signIn(String id, String password) throws RuntimeException {
        LOGGER.info("[getSignInResult] signDataHandler로 회원 정보 요청");
        Member member = memberRepository.getById(id);
        LOGGER.info("[getSignInResult] id : {} ", id);
        LOGGER.info("[getSignInResult] 패스워드 비교 수행");

        if (!passwordEncoder.matches(password, member.getPassword())) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다");
        }
        LOGGER.info("[getSignInResult] 패스워드 일치");

        LOGGER.info("[getSignInResult] signInResultDTO 객체 생성");

        SignInResponse signInResultDTO = SignInResponse.builder()
                .token(jwtProvider.createToken(
                        String.valueOf(member.getId())
                        , new ArrayList<>(Collections.singletonList(member.getRole().toString())))
                )
                .build();


        LOGGER.info("[getSignInResult] SignInResultDto 객체에 값 주입");
        setSuccessResult(signInResultDTO);
        return signInResultDTO;
    }

    @Override
    @Transactional
    public SignUpResponse signUpAdmin(SignUpAdminRequest signUpAdminRequest) {
        LOGGER.info("[signUpAdmin] 회원 가입 정보 전달");
        Member member;
        member = Member.builder()
                .id(signUpAdminRequest.getId())
                .name(signUpAdminRequest.getName())
                .pwd(passwordEncoder.encode(signUpAdminRequest.getPassword()))
                .role(MemberRoleEnum.valueOf(signUpAdminRequest.getRole()))
                .phoneNumber(signUpAdminRequest.getPhoneNumber())
                .build();

        Member savedAdmin = memberRepository.save(member);

        if (signUpAdminRequest.getRole().equals("ROLE_OPERATOR")) {
            Member adminSeq = new Member();
            adminSeq.setMemberSeq(savedAdmin.getMemberSeq());

            QRcodeCategory adminQRcodeSeq = qrCodeCategoryRepository.findByName(signUpAdminRequest.getPlace());
            System.out.println(adminQRcodeSeq.toString());
            Operator operator = Operator.builder()
                    .memberSeq(adminSeq)
                    .qRcodeCategorySeq(adminQRcodeSeq)
                    .build();

            operatorRepository.save(operator);
        }

        SignUpResponse signUpResponse = new SignUpResponse();

        LOGGER.info("[signUpAdmin] adminEntity 값이 들어왔는지 확인 후 결과값 주입");
        if (!savedAdmin.getName().isEmpty()) {
            LOGGER.info("[signUpAdmin] 정상 처리 완료 ");
            setSuccessResult(signUpResponse);
        } else {
            LOGGER.info("[signUpAdmin] 실패 처리 완료 ");
            setFailResult(signUpResponse);
        }
        return signUpResponse;
    }

    @Override
    public Member getMemberinfo(HttpServletRequest servletRequest) {
        String memberId = jwtProvider.getUserInfo(servletRequest);
        Member member = memberRepository.getById(memberId);
        return member;
    }

    @Override
    public void updateUserInfo(Member member, UserInfoUpdateRequest userInfo) {
        member.setBirth(userInfo.getBirth());
        member.setMajor(userInfo.getMajor());
        member.setNickName(userInfo.getNickName());
        member.setPhoneNumber(userInfo.getPhoneNumber());

        memberRepository.save(member);
    }

    @Override
    public String findId(String email) {
        String content = memberRepository.getIdToEmail(email); // 이메일에 매핑되는 아이디
        String subject = "Ycomplaint 아이디찾기입니다!";

        return emailSender.sendMail(EmailDTO.builder()
                .email(email)
                .content(content)
                .subject(subject)
                .mailFlag("id")
                .build());

    }

    @Override
    public String findPassword(String id) {
        Member member = memberRepository.getById(id); // 아이디로 이메일 찾기
        if (member == null) {
            LOGGER.info("[findPassword] 존재하지 않는 아이디입니다.");
            return "존재하지 않는 아이디입니다.";
        }
        String code = createCode();
        PasswordCode passwordCodeExists = passwordCodeRepository.findById(id);
        if (passwordCodeExists != null) {
            passwordCodeExists.setCode(code);
            passwordCodeRepository.save(passwordCodeExists);
        } else {
            passwordCodeRepository.save(PasswordCode.builder()
                    .code(code)
                    .member(member)
                    .build());
        }


        String subject = "Ycomplaint 비밀번호 인증번호입니다!";

        return emailSender.sendMail(EmailDTO.builder()
                .email(member.getEmail())
                .content(code)
                .subject(subject)
                .mailFlag("passwordCode")
                .build());
    }

    @Override
    @Transactional
    public String checkPasswordCode(String id, String code) {
        PasswordCode codeInfo = passwordCodeRepository.findById(id); // 디비에 저장되어 있는 코드

        if (!codeInfo.getCode().equals(code)) {
            return "잘못된 코드 번호입니다 다시 한번 확인해주세요";
        }

        String tempPassword = createCode(); // 임시 비밀번호 생성

        Member member = codeInfo.getMember();
        member.setPwd(passwordEncoder.encode(tempPassword));

        memberRepository.save(member);

        passwordCodeRepository.delete(codeInfo);


        String subject = "Ycomplaint 임시 비밀번호입니다!";

        return emailSender.sendMail(EmailDTO.builder()
                .email(member.getEmail())
                .content(tempPassword)
                .subject(subject)
                .mailFlag("passwordCode")
                .build());
    }

    @Override
    public String updatePassword(Member member, String nowPassword, String newPassword) {

        if (!passwordEncoder.matches(nowPassword, member.getPassword())) {
            return "비밀번호가 일치하지 않습니다";
        }

        member.setPwd(passwordEncoder.encode(newPassword));

        memberRepository.save(member);

        return "정상적으로 비밀번호가 변경되었습니다.";
    }

    @Override
    public boolean checkId(String id) {
        Member member = memberRepository.getById(id);
        return member == null;
    }

    @Override
    public boolean checkEmail(String email) {
        String id = memberRepository.getIdToEmail(email);
        System.out.println(id);
        return id == null;
    }

    @Override
    public QRcodeCategory getOperatorInfo(Long seq) {

        Operator operator = operatorRepository.getByMemberSeq(seq);

        return operator.getQRcodeCategorySeq();
    }

    private void setSuccessResult(SignUpResponse result) {
        result.setSuccess(true);
        result.setCode(CommonResponse.SUCCESS.getCode());
        result.setMsg(CommonResponse.SUCCESS.getMsg());
    }

    private void setFailResult(SignUpResponse result) {
        result.setSuccess(true);
        result.setCode(CommonResponse.SUCCESS.getCode());
        result.setMsg(CommonResponse.SUCCESS.getMsg());
    }

    public String createCode() {
        Random random = new Random();
        StringBuffer key = new StringBuffer();

        for (int i = 0; i < 8; i++) {
            int index = random.nextInt(4);

            switch (index) {
                case 0:
                    key.append((char) (random.nextInt(26) + 97));
                    break;
                case 1:
                    key.append((char) (random.nextInt(26) + 65));
                    break;
                default:
                    key.append(random.nextInt(9));
            }
        }
        return key.toString();
    }

}
