package com.yongin.complaint.Controller;

import com.yongin.complaint.Common.MemberRoleEnum;
import com.yongin.complaint.JPA.Entity.Member;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.util.List;

@RestController
//@RequestMapping("/test")
public class testController {

    @GetMapping(value = "/token")
    public List<String> helloWorld() {
        return MemberRoleEnum.getMemberRole();
    }

    @PostMapping("/test")
    public String SecurityContextHolderTest(@RequestBody String test) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Member member = (Member) auth.getPrincipal();
        System.out.println(member.getMemberSeq());
        System.out.println(member.getNickName());
        System.out.println(member.getId());
        System.out.println(member.getUsername());
        return member.toString();
    }

    @PostMapping(value = "/img")
    public void imgUpload(MultipartHttpServletRequest img) throws Exception {
        System.out.println(img.toString());
        String absolutePath = new File("").getAbsolutePath() + "\\";

        String path = "images/";
        File file = new File(absolutePath + path + "/" + "test.jpg");
//        img.get.transferTo(file);

    }
}
