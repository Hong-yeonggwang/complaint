package com.yongin.complaint.JPA.Entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

//@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "CHATROOM_MEMBER_RELATION")
public class ChatRoomMemberRelation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="CHATROOM_MEBER_RELATION_SEQ_PK")
    private Long chatRoomMemberRelationSeq;

    @ManyToMany
    @JoinColumn(name = "MEMBER_ID_FK")
    private List<Member> memberId;

    @ManyToMany
    @JoinColumn(name = "CHATROOM_ID_KF")
    private List<ChatRoomInfo> chatRoomId;
}
