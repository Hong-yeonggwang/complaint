package com.yongin.complaint.JPA;

import com.yongin.complaint.JPA.Entity.Member;
import com.yongin.complaint.JPA.Repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class RepositoryTest {
    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void selectTest(){
        List<Member> memberList = memberRepository.findAll();
        memberList.forEach(name -> System.out.println(name.getMemberSeq()));
    }
}
