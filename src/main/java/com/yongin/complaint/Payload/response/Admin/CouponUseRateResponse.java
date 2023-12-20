package com.yongin.complaint.Payload.response.Admin;

import lombok.*;

import java.util.Map;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CouponUseRateResponse {
    private Map<String, Long> rate;
    private String placeName;
}
