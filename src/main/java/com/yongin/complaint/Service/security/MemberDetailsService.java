package com.yongin.complaint.Service.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface MemberDetailsService {

    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
