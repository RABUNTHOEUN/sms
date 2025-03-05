package com.thoeun.sms.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class ApiResponse<T> {
    private int status;        // HTTP status code (e.g., 200, 404, 500)
    private String message;    // Human-readable message (e.g., "Success", "Student not found")
    private T data;            // The actual response data (e.g., Student object or null)
    private String timestamp;  // Time of response

    public ApiResponse(int status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.timestamp = Instant.now().toString(); // ISO-8601 timestamp
    }

    // Static factory methods for convenience
    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(200, "Success", data);
    }

    public static <T> ApiResponse<T> created(T data) {
        return new ApiResponse<>(201, "Resource created successfully", data);
    }

    public static <T> ApiResponse<T> noContent() {
        return new ApiResponse<>(204, "Resource deleted successfully", null);
    }

    public static <T> ApiResponse<T> notFound(String message) {
        return new ApiResponse<>(404, message, null);
    }

    public static <T> ApiResponse<T> error(int status, String message) {
        return new ApiResponse<>(status, message, null);
    }
}