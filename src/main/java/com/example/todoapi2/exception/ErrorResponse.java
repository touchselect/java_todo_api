package com.example.todoapi2.exception;

import lombok.Data;

@Data
public class ErrorResponse {

    private final String title;
    private final String detail;
}
