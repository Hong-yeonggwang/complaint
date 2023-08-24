package com.yongin.complaint.QRcodeGenerater;

import com.yongin.complaint.DTO.QRcode.QRcodeDTO;
import com.yongin.complaint.JPA.Entity.Member;
import org.springframework.stereotype.Component;

public interface QRcodeGenerater {
    QRcodeDTO generateQRcode(String category, String qrSerial, Member userInfo);
}
