package com.yongin.complaint.JPA.Repository;

import com.yongin.complaint.JPA.Entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
