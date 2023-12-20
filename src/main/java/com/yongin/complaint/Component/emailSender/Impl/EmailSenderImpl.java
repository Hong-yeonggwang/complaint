package com.yongin.complaint.Component.emailSender.Impl;

import com.yongin.complaint.Component.emailSender.EmailSender;
import com.yongin.complaint.DTO.Email.EmailDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.internet.MimeMessage;


@Component
public class EmailSenderImpl implements EmailSender {

    private final Logger LOGGER = LoggerFactory.getLogger(EmailSenderImpl.class);
    private final JavaMailSender javaMailSender;
    private final SpringTemplateEngine templateEngine;

    @Autowired
    public EmailSenderImpl(JavaMailSender javaMailSender, SpringTemplateEngine templateEngine) {
        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;
    }

    @Override
    public String sendMail(EmailDTO emailDTO) {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        try {
            // 1. 메일 수신자 설정
            String receive = emailDTO.getEmail(); // 보낼 메일 주소

            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
            // 1. 메일 수신자 설정
            mimeMessageHelper.setTo(receive); // 메일 수신자
            // 2. 제목 설정
            mimeMessageHelper.setSubject(emailDTO.getSubject()); // 메일 제목
            //3. 내용 설정
            mimeMessageHelper.setText(setContext(emailDTO.getContent(), emailDTO.getMailFlag()), true); // 메일 본문 내용, HTML 여부
            javaMailSender.send(mimeMessage);

            LOGGER.info("[sendEmail] 수신을 완료했습니다.");

            return "이메일 전송을 완료했습니다! 메일함을 확인해주새요";

        } catch (Exception e) {
            LOGGER.info("[sendEmail] {}", e.toString());
            return "메일 전송을 실패했습니다ㅠㅠ 잠시후 다시 시도해주세요";
        }
    }

    public String setContext(String code, String type) {
        if (type.equals("post")) {
            String[] text = code.split("<br>");
            Context context = new Context();
            context.setVariable("code", text[0]);
            context.setVariable("content", text[1]);
            return templateEngine.process(type, context);
        }
        Context context = new Context();
        context.setVariable("code", code);
        return templateEngine.process(type, context);
    }
}



