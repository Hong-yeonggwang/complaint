package com.yongin.complaint.DTO.Email;

import lombok.*;

@Setter
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmailDTO {
    private String email;
    private String subject;
    private String content;
    private String mailFlag;  // id, passwordCode, tempPassword, post
}
