package com.yongin.complaint.JPA.Repository;

import com.yongin.complaint.JPA.Entity.QRcode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface QRcodeRepository extends JpaRepository<QRcode,Long> {

    @Query("SELECT q from QRcode q join fetch q.category where q.qrCode = :SERIAL")
    QRcode existsQRcode(@Param("SERIAL") String qrCodeSerial);


}
