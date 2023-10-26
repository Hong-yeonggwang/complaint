package com.yongin.complaint.Component.QRcodeGenerater;

import com.yongin.complaint.DTO.QRcodeDTO;
import com.yongin.complaint.JPA.Entity.Member;
import com.yongin.complaint.JPA.Entity.QRcodeCategory;

public interface QRcodeGenerater {
    QRcodeDTO generateQRcode(QRcodeCategory category, String qrSerial, Member userInfo);
}
