package com.yongin.complaint.JPA.Repository;

import com.yongin.complaint.JPA.Entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Member getById(String id);

    @Query("select count(m) from Member m where m.role = 'ROLE_USER'")
    Long getUserCount();
}
