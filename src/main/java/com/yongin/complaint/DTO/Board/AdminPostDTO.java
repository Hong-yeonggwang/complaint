package com.yongin.complaint.DTO.Board;

import com.yongin.complaint.Common.BoardTagEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class AdminPostDTO {
    private String subject;
    private String content;
    private String tag;
    private Long seq;
    private String writerName;
    private String writerPhoneNumber;
    private String writerEmail;
    private String status;
    private LocalDateTime regulationDate;
    private LocalDateTime completeDate;
    private String comment;

    public AdminPostDTO(
             String subject,
             String content,
             BoardTagEnum tag,
             Long seq,
             String writerName,
             String writerPhoneNumber,
             String writerEmail,
             String status,
             LocalDateTime regulationDate,
             LocalDateTime completeDate,
             String comment
    ){
        this.subject = subject;
        this.content = content;
        this.tag = tag.toString();
        this.seq = seq;
        this.writerName = writerName;
        this.writerPhoneNumber = writerPhoneNumber;
        this.writerEmail = writerEmail;
        this.status = status;
        this.regulationDate = regulationDate;
        this.completeDate = completeDate;
        this.comment = comment;
    }

}
