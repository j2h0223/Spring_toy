package com.toy.crud.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class StudentInputDto {
    private final String name;
    private final int age;
    private final int score;
}