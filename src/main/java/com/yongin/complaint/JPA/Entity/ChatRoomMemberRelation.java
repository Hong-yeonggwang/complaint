package com.yongin.complaint.JPA.Entity;

import javax.persistence.*;

public class ChatRoomMemberRelation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="CHATROOM_MEBER_RELATION_SEQ_PK", nullable = false)
    private Long chatRoomMemberRelationSeq;

    @ManyToMany
    @JoinColumn(name = "MEMBER_ID_FK")
    private Member memberId;

    @ManyToMany
    @JoinColumn(name = "CHATROOM_ID_KF")
    private ChatRoomInfo chatRoomId;
}
