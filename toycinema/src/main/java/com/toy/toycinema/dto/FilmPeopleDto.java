package com.toy.toycinema.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FilmPeopleDto {
    private int id;
    private int peopleId;
    private int filmId;
    private LocalDateTime createdAt;
}
