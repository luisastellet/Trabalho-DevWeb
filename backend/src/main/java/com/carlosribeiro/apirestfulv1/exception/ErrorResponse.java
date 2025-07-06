package com.carlosribeiro.apirestfulv1.exception;

import java.time.LocalDateTime;
import java.util.Map;

public record ErrorResponse(
    LocalDateTime localDateTime,
    int errorCode,
    String error,
    String metodo,
    String requestUri,
    Map<String, String> map,
    String message
) {
}
