package com.nisum.registeruser.infrastructure.exception;

import lombok.Builder;

@Builder
public record ErrorResponse(
        String mensaje
) {
}
