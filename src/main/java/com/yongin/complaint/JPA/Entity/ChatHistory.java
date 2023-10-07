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
@Table(name = "CHAT_HISTORY")
public class ChatHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="CHAT_SEQ_PK")
    private Long chatSeq;

    @Column(name="CHAT_Message")
    private String chatMessage;

    @Column(name="CHAT_HISTORY_TIME")
    private LocalDateTime chatHistoryTime;

    @Column(name="CHAT_USER_ID")
    private String chatUserId;

    @Column(name="CHATROOM_ID")
    private String chatRoomId;

}
