package com.toy.toycinema.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FilmTypeDto {
    private int id;
    private int typeId;
    private int filmId;
    private LocalDateTime createdAt;
}
