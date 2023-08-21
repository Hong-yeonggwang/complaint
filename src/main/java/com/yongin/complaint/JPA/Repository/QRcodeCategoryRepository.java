package com.yongin.complaint.JPA.Repository;

import com.yongin.complaint.JPA.Entity.QRcodeCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface QRcodeCategoryRepository extends JpaRepository<QRcodeCategory,Long> {

    @Query("SELECT q FROM QRcodeCategory q WHERE q.name = :NAME AND q.qrCodeCategory = :CATEGORY")
    QRcodeCategory getPrice(@Param("NAME") String name, @Param("CATEGORY") String category);
}
