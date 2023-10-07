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
@Table(name = "CHATROOM_INFO")
public class ChatRoomInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="CHATROOM_SEQ_PK")
    private Long chatRoomSeq;

    @Column(name = "CHATROOM_ID")
    private String chatRoomId;

    @Column(name = "CHATROOM_NAME")
    private String chatRoomName;

    @Column(name = "NUMBER_OF_PEOPLE_LIMITED")
    private Integer chatRoomLimited;

    @Column(name = "CHATROOM_CREATED_DATE")
    private LocalDateTime chatRoomCreatedDate;

    @Column(name = "CHATROOM_DELETED_DATE")
    private LocalDateTime chatRoomDeletedDate;

    @Column(name = "state")
    private String state;
}
