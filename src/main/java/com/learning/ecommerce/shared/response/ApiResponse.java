package com.learning.ecommerce.shared.response;

import java.time.LocalDateTime;

public record ApiResponse<T>(
        LocalDateTime timestamp,
        int status,
        T data
) {

    public static <T> ApiResponse<T> ok(T data) {
        return new ApiResponse<>(LocalDateTime.now(), 200, data);
    }

    public static <T> ApiResponse<T> created(T data) {
        return new ApiResponse<>(LocalDateTime.now(), 201, data);
    }

}
