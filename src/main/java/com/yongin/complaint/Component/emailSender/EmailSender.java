package com.yongin.complaint.Component.emailSender;

import com.yongin.complaint.DTO.Email.EmailDTO;

public interface EmailSender {
    String sendMail(EmailDTO emailDTO);
}
