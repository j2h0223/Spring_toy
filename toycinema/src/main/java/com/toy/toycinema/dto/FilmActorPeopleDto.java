package com.toy.toycinema.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FilmActorPeopleDto {
    private int id;
    private int filmId;
    private int peopleId;
    private LocalDateTime createdAt;
}
