package com.yongin.complaint.JPA.Repository;

import com.yongin.complaint.JPA.Entity.QRcodeCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QRcodeCategoryRepository extends JpaRepository<QRcodeCategory,Long> {

    @Query("SELECT q FROM QRcodeCategory q WHERE q.name = :NAME AND q.qrcodeUsingPlace.name = :CATEGORY")
    QRcodeCategory findByName(@Param("CATEGORY") String category, @Param("NAME") String name);

    @Query("SELECT q from QRcodeCategory  q where q.name = :NAME")
    QRcodeCategory findByName(@Param("NAME") String name);

    @Override
    @Query("select q from QRcodeCategory q join fetch q.qrcodeUsingPlace p where q.status != 'hidden'")
    List<QRcodeCategory> findAll();

    @Query("select count(q) from QRcodeCategory q where q.status = 'show'")
    Long getPlaceCount();


}
