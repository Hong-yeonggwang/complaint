package com.yongin.complaint.Controller;

import com.yongin.complaint.Common.MemberRoleEnum;
import com.yongin.complaint.JPA.Entity.Member;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/test")
public class testController {

    @GetMapping(value = "/token")
    public List<String> helloWorld(){
        return MemberRoleEnum.getMemberRole();
    }

    @PostMapping("")
    public String SecurityContextHolderTest(@RequestBody String test){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Member member  = (Member)auth.getPrincipal();
        System.out.println(member.getId());
        System.out.println(member.getUsername());
        System.out.println(member.getPassword());
        return member.toString();
    }
}
