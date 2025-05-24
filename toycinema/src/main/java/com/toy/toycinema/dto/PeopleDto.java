package com.toy.toycinema.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class PeopleDto {
    private int id;
    private String name;
    private String originalName;
    private LocalDate birth;
    private String country;
    private LocalDateTime createdAt;
}
