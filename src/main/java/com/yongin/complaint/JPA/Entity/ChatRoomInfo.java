package com.yongin.complaint.JPA.Entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

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

    @Column(name = "CHATROOM_ID"/* , nullable = false */)
    private String chatRoomId; // Not Null

    @Column(name = "CHATROOM_NAME"/* , nullable = false */)
    private String chatRoomName; // Not Null

//    @ManyToOne
//    @JoinColumn(name = "CHATROOM_OWNER_FK"/* , nullable = false */)
//    private Member chatRoomOwner; // Not Null

    @Column(name = "CURRENT_NUMBER_OF_PEOPLE"/* , nullable = false */)
    private Integer currentNumberOfPeople; // Not Null

    @Column(name = "NUMBER_OF_PEOPLE_LIMITED"/* , nullable = false */)
    private Integer chatRoomLimited; // Not Null

    @Column(name = "CHATROOM_CREATED_DATE"/* , nullable = false */)
    private LocalDateTime chatRoomCreatedDate; // Not Null

//    @Column(name = "CHATROOM_DELETED_DATE")
//    private LocalDateTime chatRoomDeletedDate;

//    @Column(name = "STATE"/* , nullable = false */)
//    private boolean state; // Not Null

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "CHATROOM_MEMBERS",
            joinColumns = @JoinColumn(name = "CHATROOM_SEQ_FK"),
            inverseJoinColumns = @JoinColumn(name = "MEMBER_SEQ_FK")
    )
    private List<Member> members; // Not Null
}
