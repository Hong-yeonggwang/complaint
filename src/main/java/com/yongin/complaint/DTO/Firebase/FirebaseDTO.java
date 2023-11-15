package com.yongin.complaint.DTO.Firebase;

import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FirebaseDTO {
    private String title;
    private String body;
}
