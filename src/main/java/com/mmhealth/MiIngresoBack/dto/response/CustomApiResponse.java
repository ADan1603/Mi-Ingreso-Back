package com.mmhealth.MiIngresoBack.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Estructura estándar de respuesta de la API")
public class CustomApiResponse<T> {
    @Schema(description = "Estado de la respuesta (success/error)", example = "success")
    private String status;
    @Schema(description = "Mensaje descriptivo para el usuario", example = "Operación exitosa")
    private String message;
    @Schema(description = "Datos de la respuesta")
    private T data;

    // Constructores
    public CustomApiResponse(String status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public CustomApiResponse(String status, T data) {
        this(status, null, data);
    }

    // Getters y Setters
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
