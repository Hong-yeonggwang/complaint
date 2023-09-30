package com.yongin.complaint.Service.QRcode;

import com.yongin.complaint.JPA.Entity.QRcode;
import com.yongin.complaint.Payload.response.QRcodeResponse;
import com.yongin.complaint.JPA.Entity.Member;

import java.util.List;

public interface QRcodeService {
    QRcodeResponse useQrcode(String qrCodeSerial, Member adminInfo);

    List<QRcode> getQRcodeList(String userid);
}
