package com.mmhealth.MiIngresoBack.dto.response;

import org.springframework.http.ResponseEntity;

public class ResponseBuilder {
    public static <T> ResponseEntity<CustomApiResponse<T>> success(T data) {
        return ResponseEntity.ok(new CustomApiResponse<>("success", data));
    }

    public static <T> ResponseEntity<CustomApiResponse<T>> success(String message, T data) {
        return ResponseEntity.ok(new CustomApiResponse<>("success", message, data));
    }

    public static <T> ResponseEntity<CustomApiResponse<T>> created(String message, T data) {
        return ResponseEntity.status(201).body(new CustomApiResponse<>("success", message, data));
    }

    public static ResponseEntity<CustomApiResponse<Void>> notFound(String message) {
        return ResponseEntity.status(404).body(new CustomApiResponse<>("error", message, null));
    }

    public static ResponseEntity<CustomApiResponse<Void>> error(int statusCode, String message) {
        return ResponseEntity.status(statusCode).body(new CustomApiResponse<>("error", message, null));
    }
}
