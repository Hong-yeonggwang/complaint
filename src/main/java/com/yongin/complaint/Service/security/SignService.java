package com.yongin.complaint.Service.security;


import com.yongin.complaint.Payload.requset.SignUpAdminRequest;
import com.yongin.complaint.Payload.response.SignInResponse;
import com.yongin.complaint.Payload.requset.SignUpRequest;
import com.yongin.complaint.Payload.response.SignUpResponse;

public interface SignService {
    SignUpResponse signUp(SignUpRequest signUpRequest);
    SignInResponse signIn(String id, String password) throws RuntimeException;

    SignUpResponse signUpAdmin(SignUpAdminRequest signUpAdminRequest);
}
