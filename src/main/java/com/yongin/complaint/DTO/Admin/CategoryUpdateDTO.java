package com.yongin.complaint.DTO.Admin;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryUpdateDTO {
    private int price;
    private String name;
    private Long categorySeq;
    private String show;
}
