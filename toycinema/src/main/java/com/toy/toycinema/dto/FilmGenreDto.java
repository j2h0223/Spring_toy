package com.toy.toycinema.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FilmGenreDto {
    private int id;
    private int filmId;
    private int genreId;
    private LocalDateTime createdAt;
}
