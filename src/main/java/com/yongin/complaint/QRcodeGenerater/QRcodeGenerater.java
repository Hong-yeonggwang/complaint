package com.yongin.complaint.QRcodeGenerater;

import com.yongin.complaint.DTO.QRcodeDTO;
import com.yongin.complaint.JPA.Entity.Member;

public interface QRcodeGenerater {
    QRcodeDTO generateQRcode(String category, String qrSerial, Member userInfo);
}
