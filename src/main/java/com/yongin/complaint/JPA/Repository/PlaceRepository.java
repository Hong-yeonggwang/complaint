package com.yongin.complaint.JPA.Repository;

import com.yongin.complaint.DTO.Admin.QRcodeDateDTO;
import com.yongin.complaint.DTO.Admin.QRcodeStatisticsDTO;
import com.yongin.complaint.JPA.Entity.Member;
import com.yongin.complaint.JPA.Entity.Place;
import com.yongin.complaint.JPA.Entity.QRcode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface PlaceRepository extends JpaRepository<Place,Long> {

}
