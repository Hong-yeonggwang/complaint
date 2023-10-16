package com.yongin.complaint.QRcodeGenerater.Impl;

import com.yongin.complaint.DTO.QRcodeDTO;
import com.yongin.complaint.JPA.Entity.Member;
import com.yongin.complaint.JPA.Entity.QRcode;
import com.yongin.complaint.JPA.Entity.QRcodeCategory;
import com.yongin.complaint.JPA.Repository.QRcodeCategoryRepository;
import com.yongin.complaint.JPA.Repository.QRcodeRepository;
import com.yongin.complaint.QRcodeGenerater.QRcodeGenerater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;

@Component
public class QRcodeGeneraterImpl implements QRcodeGenerater {

    private QRcodeRepository qeCodeRepository;
    private QRcodeCategoryRepository qrCodeCategoryRepository;

    @Autowired
    public QRcodeGeneraterImpl(QRcodeRepository qeCodeRepository,QRcodeCategoryRepository qrCodeCategoryRepository){
        this.qeCodeRepository = qeCodeRepository;
        this.qrCodeCategoryRepository = qrCodeCategoryRepository;
    }
    @Override
    public QRcodeDTO generateQRcode(QRcodeCategory category, String ticketInfo, Member userInfo) {
        String combineString = category.getName() + ticketInfo + userInfo.getId(); // 카테고리,티켓정보,생성자이름 조합
        String qrCodeSerial = generateSHA256(combineString); // 정보 조합 문자열 sha256 돌림

        QRcode qrCodeExist = qeCodeRepository.existsQRcode(qrCodeSerial); // qr코드serial이 디비에 있는건지 확인

        if(qrCodeExist == null){
//            qr코드 디비에 저장
            QRcode qrCode = QRcode.builder()
                    .qrCode(qrCodeSerial)
                    .category(category)
                    .owner(userInfo)
                    .regulationDate(LocalDateTime.now())
                    .build();
            qeCodeRepository.save(qrCode);
            return QRcodeDTO.builder().category(category).qrSerial(qrCodeSerial).build();
        }
        else{
//            예외던지기
            return null;
        }
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
