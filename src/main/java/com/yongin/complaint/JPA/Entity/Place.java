package com.yongin.complaint.JPA.Entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "PLACE")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PLACE_SEQ_PK")
    private Long placeSeq;

    @Column(name = "NAME")
    private String name;
}
