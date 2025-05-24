package com.toy.crud.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
public class StudentDto {
    private long id;
    private String name;
    private int age;
    private int score;
}
