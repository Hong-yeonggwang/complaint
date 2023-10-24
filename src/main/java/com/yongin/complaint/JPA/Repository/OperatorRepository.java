package com.yongin.complaint.JPA.Repository;

import com.yongin.complaint.DTO.Admin.OperatorDTO;
import com.yongin.complaint.JPA.Entity.Operator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OperatorRepository extends JpaRepository<Operator,Long> {

    @Query("select new com.yongin.complaint.DTO.Admin.OperatorDTO(o.memberSeq.name, o.qRcodeCategorySeq, o.memberSeq.phoneNumber) from Operator o")
    List<OperatorDTO> getOperator();
}
