package com.yongin.complaint.DAO.Impl;

import com.yongin.complaint.DAO.QRcodeDAO;
import com.yongin.complaint.DTO.Member.QRcodeLogDTO;
import com.yongin.complaint.JPA.Entity.Member;
import com.yongin.complaint.JPA.Entity.QRcode;
import com.yongin.complaint.JPA.Repository.QRcodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    public List<QRcode> getQRcodeList(String memberId) {
        return qrCodeRepository.getQrcodeList(memberId);
    }

    @Override
    public void qrcodeUpdate(QRcode qrCode) {
        qrCodeRepository.save(qrCode);
    }

    @Override
    public List<QRcodeLogDTO> getQRcodeLog(Long seq) {
        return qrCodeRepository.getByUserSeq(seq);
    }
}
