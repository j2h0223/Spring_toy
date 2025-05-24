package com.toy.toycinema.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class SelectFilmReservationDto {
    private LocalDate date;
    private LocalDate today;
    private LocalTime time;
    private double price;
    private int ticketCount;
    private int filmId;
    private String  filmName;
    private int theaterId;
    private String  theaterName;
    private int boxTypeId;
    private int boxId;
    private int typeId;
    private int filmTypeId;
}
