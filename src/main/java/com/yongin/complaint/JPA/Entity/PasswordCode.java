package com.yongin.complaint.JPA.Entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "PASSWORDCODE")
public class PasswordCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PASSWORDCODE_SEQ_PK")
    private Long passwordCodeSeq;

    @OneToOne
    @JoinColumn(name = "MEMBER_FK")
    private Member member;

    @Column(name = "CODE")
    private String code;
}
