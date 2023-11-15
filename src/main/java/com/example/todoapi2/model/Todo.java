package com.example.todoapi2.model;

import lombok.Data;


@Data
public class Todo {

    private Long id;
    private String title;
    private Status status;
    private String details;

}
