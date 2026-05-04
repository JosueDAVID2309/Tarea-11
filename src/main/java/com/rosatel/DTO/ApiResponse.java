package com.rosatel.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;

@Setter
@AllArgsConstructor
@Data
public class ApiResponse<T> {
    private boolean success;
    private T data;
    private String message;
}
