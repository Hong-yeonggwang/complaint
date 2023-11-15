package com.yongin.complaint.DTO.Board;

import com.yongin.complaint.Common.BoardTagEnum;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class UserPostDTO {
    private String subject;
    private String content;
    private String tag;
    private Long seq;
    private String write;
    private String status;
    private LocalDateTime regulationDate;
    private LocalDateTime completeDate;

    public UserPostDTO(
             String subject,
             String content,
             BoardTagEnum tag,
             Long seq,
             String write,
             String status,
             LocalDateTime regulationDate,
             LocalDateTime completeDate
    ){
        this.subject = subject;
        this.content = content;
        this.tag = tag.toString();
        this.seq = seq;
        this.write = write;
        this.status = status;
        this.regulationDate = regulationDate;
        this.completeDate = completeDate;
    }

}
