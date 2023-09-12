package com.yongin.complaint.Service.security.Impl;

import com.yongin.complaint.JPA.Repository.MemberRepository;
import com.yongin.complaint.Service.security.MemberDetailsService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class MemberDetailsServiceImpl implements MemberDetailsService {
    private final Logger LOGGER = LoggerFactory.getLogger(MemberDetailsServiceImpl.class);

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username){
        LOGGER.info("[loadUserByUsername] loadUserByUsername 수행. username:{}",username);
        return memberRepository.getById(username);
    }

}
