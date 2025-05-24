package com.toy.toycinema.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class FilmDto {
    private int id;
    private String filmName;
    private String originalName;
    private String story;
    private LocalDate openingDay;
    private LocalDate endDay;
    private int runningTime;
    private String country;
    private int filmRating;
    private boolean isNowPlaying;
    private String urlMain;
    private LocalDateTime createdAt;
}
