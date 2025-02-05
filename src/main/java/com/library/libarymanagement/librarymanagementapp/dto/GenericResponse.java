package com.library.libarymanagement.librarymanagementapp.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenericResponse {
    private int statusCode;
    private String message;
    private Object data;

    public GenericResponse(int statusCode, String message, Object data) {
        this.statusCode = statusCode;
        this.message = message;
        this.data = data;
    }
}
