package com.yongin.complaint.DAO.Impl;

import com.yongin.complaint.DAO.QRcodeDAO;
import com.yongin.complaint.JPA.Entity.QRcode;
import com.yongin.complaint.JPA.Repository.QRcodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
}
