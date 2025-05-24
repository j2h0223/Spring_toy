package com.toy.crud.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class StudentOutputDtoForm {
    private long id;
    private String name;
    private int age;
    private int score;
}
