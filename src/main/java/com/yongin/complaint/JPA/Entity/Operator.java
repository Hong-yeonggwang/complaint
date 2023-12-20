package com.yongin.complaint.JPA.Entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "OPERATOR")
public class Operator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OPERATOR_SEQ_PK")
    private Long operatorSeq;

    @OneToOne
    @JoinColumn(name = "MEMBER_FK")
    private Member memberSeq;

    @OneToOne
    @JoinColumn(name = "QRCODECATEGORY_FK")
    private QRcodeCategory qRcodeCategorySeq;
}
