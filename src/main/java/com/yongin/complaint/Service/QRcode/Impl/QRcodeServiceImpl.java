package com.yongin.complaint.Service.QRcode.Impl;

import com.yongin.complaint.DAO.QRcodeDAO;
import com.yongin.complaint.Payload.response.QRcodeResponse;
import com.yongin.complaint.JPA.Entity.Member;
import com.yongin.complaint.JPA.Entity.QRcode;
import com.yongin.complaint.QRcodeGenerater.QRcodeGenerater;
import com.yongin.complaint.Service.QRcode.QRcodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class QRcodeServiceImpl implements QRcodeService {
    private QRcodeDAO qrCodeDAO;
    private QRcodeGenerater qrCodeGenerater;

    @Autowired
    public QRcodeServiceImpl( QRcodeDAO qrCodeDAO, QRcodeGenerater qrCodeGenerater){
        this.qrCodeDAO = qrCodeDAO;
        this.qrCodeGenerater = qrCodeGenerater;
    }
    @Override
    @Transactional
    public QRcodeResponse useQrcode(String qrCodeSerial, Member adminInfo){
        QRcode qrCode = qrCodeDAO.existQRcode(qrCodeSerial);

        if(qrCode == null){
            //디비에 존재하지 않는 시리얼 넘버임.
            return QRcodeResponse.builder()
                    .msg("유효하지 않는 qrcode입니다.")
                    .qRcode(null)
                    .build();
        }
        else if(qrCode != null && (qrCode.getUseDate() == null && qrCode.getStatus().equals("none"))){ // 디비에 존재하는 시리얼이고 사용되지 않는 시리얼 넘버임.
            //디비에 존재하는 시리얼 넘버임.
            qrCode.setUseDate(LocalDateTime.now());
            qrCode.setStatus("used");
            qrCodeDAO.qrcodeUpdate(qrCode);

            return QRcodeResponse.builder()
                    .msg("사용이 완료되었습니다.")
                    .qRcode(qrCode.getQrCode())
                    .build();

        }
        return null;
    }

    @Override
    public List<QRcode> getQRcodeList(String userId) {
        return qrCodeDAO.getQRcodeList(userId);
    }

}
