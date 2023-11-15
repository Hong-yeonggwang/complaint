package com.yongin.complaint.JPA.Entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "COUPON")
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="COUPON_SEQ_PK")
    private Long qrCategorySeq;

    @Column(name = "SERIAL")
    private String serial;

    @OneToOne
    @JoinColumn(name = "CATEGORY_FK")
    private QRcodeCategory qrCodeCategory;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "USEDMEMBER_SEQ")
    private Long userMemberSeq;
}
