package com.yongin.complaint.JPA.Repository;

import com.yongin.complaint.DTO.Admin.QRcodeStatisticsDTO;
import com.yongin.complaint.DTO.Admin.QRcodeDateDTO;
import com.yongin.complaint.JPA.Entity.Member;
import com.yongin.complaint.JPA.Entity.QRcode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;


public interface QRcodeRepository extends JpaRepository<QRcode,Long> {

    @Query("SELECT q from QRcode q join fetch q.category where q.qrCode = :SERIAL")
    QRcode existsQRcode(@Param("SERIAL") String qrCodeSerial);

    @Query("select q from QRcode q join fetch q.category join fetch q.owner m where m.id = :MEMBERID and q.useDate = null")
    List<QRcode> getQrcodeList(@Param("MEMBERID") String memberId);

    List<QRcode> getByOwner(Member memberSeq);

    @Query("select new com.yongin.complaint.DTO.Admin.QRcodeStatisticsDTO(COUNT(q),q.category.name) from QRcode q group by q.category")
    List<QRcodeStatisticsDTO> getQrcodeCount();

    @Query("select new com.yongin.complaint.DTO.Admin.QRcodeStatisticsDTO(q.status,COUNT(q),q.category.name) from QRcode q where q.status = 'used' group by q.category")
    List<QRcodeStatisticsDTO> getQrcoceUseCount();

    @Query("select new com.yongin.complaint.DTO.Admin.QRcodeDateDTO(COUNT(e),CONCAT(EXTRACT(YEAR FROM e.regulationDate), '-', LPAD(EXTRACT(MONTH FROM e.regulationDate), 2, '0'), '-', LPAD(EXTRACT(DAY FROM e.regulationDate), 2, '0'))) " +
            "from QRcode e group by CONCAT(EXTRACT(YEAR FROM e.regulationDate), '-', LPAD(EXTRACT(MONTH FROM e.regulationDate), 2, '0'), '-', LPAD(EXTRACT(DAY FROM e.regulationDate), 2, '0'))")
    List<QRcodeDateDTO> getQRcodeCountToDate();

    @Query("select new com.yongin.complaint.DTO.Admin.QRcodeDateDTO(COUNT(e),CONCAT(EXTRACT(YEAR FROM e.regulationDate), '-', LPAD(EXTRACT(MONTH FROM e.regulationDate), 2, '0'), '-', LPAD(EXTRACT(DAY FROM e.regulationDate), 2, '0'))) " +
            "from QRcode e where e.status = 'used' group by CONCAT(EXTRACT(YEAR FROM e.regulationDate), '-', LPAD(EXTRACT(MONTH FROM e.regulationDate), 2, '0'), '-', LPAD(EXTRACT(DAY FROM e.regulationDate), 2, '0'))")
    List<QRcodeDateDTO> getQRcodeCountToDateWithUsed();

    @Query("select count(e)" +
            "from QRcode e where " +
            "CONCAT(EXTRACT(YEAR FROM e.regulationDate), '-', LPAD(EXTRACT(MONTH FROM e.regulationDate), 2, '0'), '-', LPAD(EXTRACT(DAY FROM e.regulationDate), 2, '0')) = :TODAY")
    Long getTodayUsedQrcodeCount(@Param(value = "TODAY")String today);

    @Query("select count(e)" +
            "from QRcode e where " +
            "CONCAT(EXTRACT(YEAR FROM e.useDate), '-', LPAD(EXTRACT(MONTH FROM e.useDate), 2, '0'), '-', LPAD(EXTRACT(DAY FROM e.useDate), 2, '0')) = :TODAY")
    Long getTodayCreatedQrcodeCount(@Param(value = "TODAY")String today);

    @Query("select count(q) from QRcode q where q.status ='none' and q.useDate= null")
    Long getRemainQrcodeCount();

}
