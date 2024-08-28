package com.stenmartin.project.booking_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse implements ApiResponse {
    private String code;
    private String message;
    private boolean successful;

    @Override
    public boolean isSuccessful() {
        return successful;
    }

    @Override
    public String GetStatusCode() {
        return code;
    }
}
