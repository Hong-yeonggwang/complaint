package com.yongin.complaint.QRcodeGenerater.Impl;

import com.yongin.complaint.DTO.QRcode.QRcodeDTO;
import com.yongin.complaint.JPA.Entity.Member;
import com.yongin.complaint.JPA.Entity.QRcode;
import com.yongin.complaint.JPA.Repository.QRcodeRepository;
import com.yongin.complaint.QRcodeGenerater.QRcodeGenerater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component
public class QRcodeGeneraterImpl implements QRcodeGenerater {

    private QRcodeRepository repository;

    @Autowired
    public QRcodeGeneraterImpl(QRcodeRepository repository){
        this.repository = repository;
    }
    @Override
    public QRcodeDTO generateQRcode(String category, String qrSerial, Member userInfo) {
        String combineString = category + qrSerial + userInfo.getId(); // 정보 조합
        String qrCodeSerial = generateSHA256(combineString); // 정보 조합 문자열 sha256 돌림

        QRcode qrCodeExist = repository.existsQRcode(qrCodeSerial); // qr코드serial이 디비에 있는건지 확인

        if(qrCodeExist == null){
//            qr코드 디비에 저장
        }
        else{
//            예외던지기
        }
        return QRcodeDTO.builder().category(category).qrSerial(qrCodeSerial).build();
    }

    public static String generateSHA256(String input) {
        try {
            MessageDigest sha256Digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = sha256Digest.digest(input.getBytes());

            String hashHex = DatatypeConverter.printHexBinary(hashBytes).toLowerCase();
            return hashHex;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
