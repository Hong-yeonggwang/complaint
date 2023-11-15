package com.yongin.complaint.JPA.Repository;

import com.yongin.complaint.DTO.Admin.UserInfoDTO;
import com.yongin.complaint.JPA.Entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Member getById(String id);

    @Query("select count(m) from Member m where m.role = 'ROLE_USER'")
    Long getUserCount();

    @Query("select m.id from Member m where m.email = :EMAIL")
    String getIdToEmail(@Param(value = "EMAIL") String email);

    @Query("select new com.yongin.complaint.DTO.Admin.UserInfoDTO(m.name,m.nickName,m.major,m.phoneNumber,m.email) from Member m where m.role = 'ROLE_USER'")
    List<UserInfoDTO> getUserInfo();
}
