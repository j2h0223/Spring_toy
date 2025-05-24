package com.toy.toycinema.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FilmReservationDto {
    private int id;
    private int count;
    private int seat;
    private int filmPlayingTableId;
    private int memberId;
    private LocalDateTime createdAt;
}
