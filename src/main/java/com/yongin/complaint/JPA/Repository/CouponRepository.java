package com.yongin.complaint.JPA.Repository;

import com.yongin.complaint.DTO.Admin.CouponStatisticsDTO;
import com.yongin.complaint.JPA.Entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CouponRepository extends JpaRepository<Coupon,Long> {

    @Query("SELECT c FROM Coupon c join fetch c.qrCodeCategory q WHERE c.serial = :SERIAL")
    Coupon getBySerial(@Param("SERIAL") String serial);

    @Query("select new com.yongin.complaint.DTO.Admin.CouponStatisticsDTO(COUNT(c),c.qrCodeCategory.name) from Coupon c group by  c.qrCodeCategory")
    List<CouponStatisticsDTO> getCouponCount();

    @Query("select new com.yongin.complaint.DTO.Admin.CouponStatisticsDTO(c.status,COUNT(c),c.qrCodeCategory.name) from Coupon c  where c.status = 'use' group by  c.qrCodeCategory")
    List<CouponStatisticsDTO> getCouponUseCount();
}
