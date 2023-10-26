package com.yongin.complaint.JPA;

import com.yongin.complaint.DTO.Email.EmailDTO;
import com.yongin.complaint.Component.QRcodeGenerater.emailSender.Impl.EmailSenderImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailTest {
    @Autowired
    EmailSenderImpl emailService;

    @Test
    public void emailTest(){
        EmailDTO emailDTO = new EmailDTO();
        emailDTO.setEmail("chamsk0808@naver.com");
        emailService.sendMail(emailDTO);
    }
}
