package com.yongin.complaint.DTO.Admin;

import com.yongin.complaint.JPA.Entity.Place;
import com.yongin.complaint.JPA.Entity.QRcodeCategory;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryPlaceDTO {
    private List<QRcodeCategory> category;
    private List<Place> place;
}
