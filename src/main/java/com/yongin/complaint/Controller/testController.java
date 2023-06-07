package com.yongin.complaint.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class testController {

    @GetMapping(value = "/token")
    public void helloWorld(@RequestParam("hello") String hello){
        System.out.println(hello);
    }
}
