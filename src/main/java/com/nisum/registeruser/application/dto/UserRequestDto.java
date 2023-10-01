package com.nisum.registeruser.application.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record UserRequestDto(
        String name,
        String email,
        String password,
        List<PhoneDto> phones
) {}
