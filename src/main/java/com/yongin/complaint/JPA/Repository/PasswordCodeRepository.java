package com.yongin.complaint.JPA.Repository;

import com.yongin.complaint.JPA.Entity.PasswordCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PasswordCodeRepository extends JpaRepository<PasswordCode, Long> {
    @Query("select p from PasswordCode p join fetch p.member m where m.id = :ID")
    PasswordCode findById(@Param(value = "ID") String id);


}
