package com.thh3;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.thh3.annotation.MaskCarNo;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
@JsonFilter("MaskAny")
public class CarNoDTO {
    @MaskCarNo
    private String val;
}
