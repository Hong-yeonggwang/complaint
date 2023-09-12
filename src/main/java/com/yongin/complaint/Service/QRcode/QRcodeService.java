package com.yongin.complaint.Service.QRcode;

import com.yongin.complaint.Payload.response.QRcodeResponse;
import com.yongin.complaint.JPA.Entity.Member;

public interface QRcodeService {
    QRcodeResponse useQrcode(String qrCodeSerial, Member adminInfo);
}
