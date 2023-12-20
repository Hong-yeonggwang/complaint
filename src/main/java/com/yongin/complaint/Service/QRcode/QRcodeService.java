package com.yongin.complaint.Service.QRcode;

import com.yongin.complaint.DTO.Member.QRcodeLogDTO;
import com.yongin.complaint.JPA.Entity.QRcode;
import com.yongin.complaint.Payload.response.QRcodeResponse;

import java.util.List;

public interface QRcodeService {
    QRcodeResponse useQrcode(String qrCodeSerial, Long qrcodeCategorySeq);

    List<QRcode> getQRcodeList(String userid);

    List<QRcodeLogDTO> getQRcodeLog(Long seq);
}
