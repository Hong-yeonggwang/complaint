package com.yongin.complaint.DAO.Impl;

import com.yongin.complaint.DAO.QRcodeDAO;
import com.yongin.complaint.JPA.Entity.Member;
import com.yongin.complaint.JPA.Entity.QRcode;
import com.yongin.complaint.JPA.Repository.QRcodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class QRcodeDAOImpl implements QRcodeDAO {
    private QRcodeRepository qrCodeRepository;

    @Autowired
    public QRcodeDAOImpl(QRcodeRepository qrCodeRepository){
        this.qrCodeRepository = qrCodeRepository;
    }
    @Override
    public QRcode existQRcode(String serial) {
        return qrCodeRepository.existsQRcode(serial);
    }
    @Override
    public List<QRcode> getQRcodeList(String memberId) {
        return qrCodeRepository.getQrcodeList(memberId);
    }
}
