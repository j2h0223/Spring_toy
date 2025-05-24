package com.toy.toycinema.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FilmImageDto {
    private int id;
    private int filmId;
    private String originalFileName;
    private String urlSub;
    private LocalDateTime dataTime;
}
