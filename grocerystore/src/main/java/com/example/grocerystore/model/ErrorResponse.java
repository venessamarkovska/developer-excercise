package com.example.grocerystore.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
public class ErrorResponse {
    @NonNull
    private Integer status;
    private String message;
}
