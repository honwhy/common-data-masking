package com.thh3;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.thh3.annotation.MaskTag;
import com.thh3.annotation.MaskType;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
@JsonFilter("MaskAny")
@MaskTag
public class AnyDTO {
    @MaskTag(type = MaskType.CAR_NO)
    private String val1;
    @MaskTag(type = MaskType.MOBILE)
    private String val2;

}
