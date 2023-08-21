package com.yongin.complaint.JPA.Repository;

import com.yongin.complaint.JPA.Entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CouponRepository extends JpaRepository<Coupon,Long> {

    @Query("SELECT c FROM Coupon c join fetch c.qrCodeCategory q WHERE c.serial = :SERIAL")
    Coupon getBySerial(@Param("SERIAL") String serial);
}
