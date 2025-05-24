package com.toy.toycinema.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class GenreDto {
    private int id;
    private String genreName;
    private LocalDateTime createdAt;
}
