package com.yongin.complaint.JPA.Entity;

import com.yongin.complaint.Common.BoardTagEnum;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "BOARD")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BOARD_SEQ_PK")
    private Long boardSeq;

    @Column(name = "TAG")
    @Enumerated(EnumType.STRING)
    private BoardTagEnum tag;

    @Column(name = "SUBJECT")
    private String subject;

    @Column(name = "CONTENT")
    private String content;

    @Column(name = "COMMENT")
    private String comment;

    @Column(name = "REGULATION_DATE")
    private LocalDateTime regulationDate;

    @Column(name = "COMPLETE_DATE")
    private LocalDateTime completeDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "MEMBER_SEQ_FK")
    private Member writer;


    @Column(name = "STATUS")
    private String status;

}
