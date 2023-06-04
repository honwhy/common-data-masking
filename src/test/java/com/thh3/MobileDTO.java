package com.thh3;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.thh3.annotation.MaskTag;
import com.thh3.annotation.MaskType;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
@JsonFilter("MaskAny")
public class MobileDTO {
    @MaskTag(type = MaskType.MOBILE)
    private String val;
}
