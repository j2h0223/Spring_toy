package com.toy.crud.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
public class StudentInputDtoForm {
    private String name;
    private int age;
    private int score;
}
