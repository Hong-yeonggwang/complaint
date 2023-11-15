package com.yongin.complaint;

import com.yongin.complaint.Component.fireBase.FireBasePush;
import com.yongin.complaint.DTO.Firebase.FirebaseDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class firebaseTest {
    @Autowired
    FireBasePush fireBasePush;

    @Test
    public void test(){
        fireBasePush.sendPush(FirebaseDTO.builder()
                .title("[] 새로운 문의글입니다!")
                .body("boardWriteDTO.getSubject()")
                .build());
    }
}
