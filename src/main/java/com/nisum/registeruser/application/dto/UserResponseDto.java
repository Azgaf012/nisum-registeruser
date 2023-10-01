package com.nisum.registeruser.application.dto;

import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record UserResponseDto (
        String id,
        LocalDate created,
        LocalDate modified,
        LocalDate last_login,
        String token,
        Boolean isActive
) {}
