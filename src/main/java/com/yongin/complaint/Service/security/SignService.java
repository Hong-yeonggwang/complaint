package com.yongin.complaint.Service.security;


import com.yongin.complaint.JPA.Entity.Member;
import com.yongin.complaint.Payload.requset.SignUpAdminRequest;
import com.yongin.complaint.Payload.requset.UserInfoUpdateRequest;
import com.yongin.complaint.Payload.response.SignInResponse;
import com.yongin.complaint.Payload.requset.SignUpRequest;
import com.yongin.complaint.Payload.response.SignUpResponse;

import javax.servlet.http.HttpServletRequest;

public interface SignService {
    SignUpResponse signUp(SignUpRequest signUpRequest);
    SignInResponse signIn(String id, String password) throws RuntimeException;

    SignUpResponse signUpAdmin(SignUpAdminRequest signUpAdminRequest);

    Member getMemberinfo(HttpServletRequest servletRequest);

    void updateUserInfo(Member member,UserInfoUpdateRequest userInfo);

    String findId(String email);
    String findPassword(String id);
    String checkPasswordCode(String id, String code);

    String updatePassword(Member member,String nowPassword ,String newPassword);

    boolean checkId(String id);
    boolean checkEmail(String email);
}
