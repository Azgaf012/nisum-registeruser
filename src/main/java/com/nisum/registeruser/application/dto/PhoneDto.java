package com.nisum.registeruser.application.dto;

import lombok.Builder;

@Builder
public record PhoneDto(
        String number,
        Integer cityCode,
        Integer countryCode
) {
}
