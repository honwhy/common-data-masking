package com.thh3;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.thh3.annotation.MaskMobile;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
@JsonFilter("MaskAny")
public class MobileDTO {
    @MaskMobile
    private String val;
}
