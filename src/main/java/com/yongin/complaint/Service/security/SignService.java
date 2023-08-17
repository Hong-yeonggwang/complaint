package com.yongin.complaint.Service.security;


import com.yongin.complaint.DTO.security.SignInResultDTO;
import com.yongin.complaint.DTO.security.SignUpResultDTO;

public interface SignService {
    SignUpResultDTO signUp(String id, String password, String name, String role);
    SignInResultDTO signIn(String id, String password) throws RuntimeException;
}
