package com.toy.toycinema.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
public class FilmPlayingTableDto {
    private int id;
    private LocalDate date;
    private LocalTime time;
    private double price;
    private int filmId;
    private int boxTypeId;
    private int typeId;
    private LocalDateTime createdAt;
}
