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
    @Column(name="CHAT_SEQ_PK"/* , nullable = false */)
    private Long chatSeq;

    @Column(name="CHAT_MESSAGE"/* , nullable = false */)
    private String chatMessage; // Not Null

    @Column(name="CHAT_HISTORY_TIME"/* , nullable = false */)
    private LocalDateTime chatHistoryTime; // Not Null

    @ManyToOne
    @JoinColumn(name="CHAT_USER_ID_FK"/* , nullable = false */)
    private Member chatUserId; // Not Null

    @OneToOne
    @JoinColumn(name = "CHATROOM_ID_FK"/* , nullable = false */)
    private ChatRoomInfo chatRoomId; // Not Null

}
