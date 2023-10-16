package com.yongin.complaint.JPA.Repository;

import com.yongin.complaint.JPA.Entity.Member;
import com.yongin.complaint.JPA.Entity.QRcode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface QRcodeRepository extends JpaRepository<QRcode,Long> {

    @Query("SELECT q from QRcode q join fetch q.category where q.qrCode = :SERIAL")
    QRcode existsQRcode(@Param("SERIAL") String qrCodeSerial);

    @Query("select q from QRcode q join fetch q.category join fetch q.owner m where m.id = :MEMBERID and q.useDate = null")
    List<QRcode> getQrcodeList(@Param("MEMBERID") String memberId);

    List<QRcode> getByOwner(Member memberSeq);


}
