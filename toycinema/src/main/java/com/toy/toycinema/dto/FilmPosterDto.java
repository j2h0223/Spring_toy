package com.toy.toycinema.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FilmPosterDto {
    private int id;
    private int filmId;
    private String originalName;
    private String urlSub;
    private LocalDateTime createdAt;
}
