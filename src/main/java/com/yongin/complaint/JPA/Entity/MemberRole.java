package com.yongin.complaint.JPA.Entity;

import com.yongin.complaint.Common.MemberRoleEnum;
import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "MEMBER_ROLE")
public class MemberRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="MEMBER_SEQ_PK")
    private Long roleSeq;

    @Column(name = "ROLE")
    @Enumerated(EnumType.STRING)
    private MemberRoleEnum role;

}
