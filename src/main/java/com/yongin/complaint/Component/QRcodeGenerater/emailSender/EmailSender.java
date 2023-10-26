package com.yongin.complaint.Component.QRcodeGenerater.emailSender;

import com.yongin.complaint.DTO.Email.EmailDTO;

public interface EmailSender {
    String sendMail(EmailDTO emailDTO);
}
