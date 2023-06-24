package com.yongin.complaint.JPA.Entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "QRCODE_CATEGORY")
public class QRcodeCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="QRCODE_CATEGORY_SEQ_PK")
    private Long qrCategorySeq;

    @Column(name = "NAME")
    private String name;

    @Column(name = "PRICE")
    private int price;

    @Column(name = "QRCODE_CATEGORY")
    private String qrCodeCategory;
}
