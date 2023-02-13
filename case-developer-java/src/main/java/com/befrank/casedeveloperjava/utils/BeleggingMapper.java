package com.befrank.casedeveloperjava.utils;

import com.befrank.casedeveloperjava.model.Belegging;
import com.befrank.casedeveloperjava.model.BeleggingDto;

public class BeleggingMapper {
    public static BeleggingDto toBeleggingDto(Belegging belegging) {
        BeleggingDto dto = new BeleggingDto();
        dto.setFonds(belegging.getFonds());
        dto.setWaarde(belegging.getWaarde());
        return dto;
    }
}
