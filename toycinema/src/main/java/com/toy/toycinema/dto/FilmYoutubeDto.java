package com.toy.toycinema.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FilmYoutubeDto {
    private int id;
    private int filmId;
    private String urlYoutube;
    private LocalDateTime createdAt;
}
