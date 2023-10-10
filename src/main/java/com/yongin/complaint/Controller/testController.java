package com.yongin.complaint.Controller;

import com.yongin.complaint.Common.MemberRoleEnum;
import com.yongin.complaint.JPA.Entity.MemberRole;
import com.yongin.complaint.JPA.Repository.MemberRoleRepository;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
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
    public String Test(@RequestBody String test){
        return test;
    }
}
