package com.yongin.complaint.Payload.response.Admin;

import com.yongin.complaint.JPA.Entity.Place;
import com.yongin.complaint.JPA.Entity.QRcodeCategory;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Builder
public class CategoryDTO {
    private List<Place> place;
    private List<QRcodeCategory> category;

    public CategoryDTO(List<Place> place, List<QRcodeCategory> category) {
        this.place = place;
        this.category = category;
    }
}
