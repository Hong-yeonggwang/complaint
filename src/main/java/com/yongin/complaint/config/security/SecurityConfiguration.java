package com.yongin.complaint.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final JwtProvider jwtProvider;

    @Autowired
    public SecurityConfiguration(JwtProvider jwtProvider){
        this.jwtProvider = jwtProvider;
    }
    @Override
    protected void configure (HttpSecurity httpSecurity) throws Exception{
        httpSecurity.httpBasic().disable()
                .csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                .antMatchers("/v1/auth/sign-in","/v1/auth/sign-up").permitAll()
                .antMatchers("/v1/auth/sign-up/admin").hasRole("ADMIN") // admin 경로는 ADMIN 역할을 가진 사용자에게만 허용
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().accessDeniedHandler(new CustomAccessDeniedHandler())
                .and()
                .exceptionHandling().authenticationEntryPoint(new CustomAuthenticationEntryPoint())
                .and()
                .addFilterBefore(new JwtAuthenticationFilter(jwtProvider), UsernamePasswordAuthenticationFilter.class);

    }
    @Override
    public void configure(WebSecurity webSecurity){
        webSecurity.ignoring().antMatchers("/v1/chat/**"); //,"/getChatRoom"
    }
}
