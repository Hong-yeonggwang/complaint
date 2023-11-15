package com.yongin.complaint.DTO.Board;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardWriteDTO {
    private String subject;
    private String content;
    private String tag;
}
