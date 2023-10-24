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

    @Column(name="MESSAGE_TYPE")
    private String messageType;

    @Column(name="CHAT_MESSAGE"/* , nullable = false */)
    private String msg; // Not Null

    @Column(name="CHAT_HISTORY_TIME"/* , nullable = false */)
    private LocalDateTime chatHistoryTime; // Not Null

//    @ManyToOne
    @Column(name="MEMBER_SEQ")
    private Long memberSeq; // Not Null

    @Column(name="SENDER_NICKNAME")
    private String nickName; // Not Null

//    @ManyToOne
    @Column(name = "CHATROOM_ID")
    private String chatRoomId; // Not Null

}
