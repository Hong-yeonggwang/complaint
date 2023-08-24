package com.yongin.complaint.JPA.Entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "QRCODE")
@ToString
public class QRcode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="QRCODE_SEQ_PK")
    private Long qrCodeSeq;

    @Column(name = "QRCODE")
    private String qrCode;

    @OneToOne
    @JoinColumn(name = "CATEGORY_FK")
    private QRcodeCategory category;

    @Column(name = "REGULATION_DATE")
    private LocalDateTime regulationDate;

    @Column(name = "USE_DATE")
    private LocalDateTime useDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_SEQ_FK")
    private Member owner;
}
