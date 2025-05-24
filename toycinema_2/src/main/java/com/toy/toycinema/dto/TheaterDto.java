package com.toy.toycinema.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
public class TheaterDto {
    private int id;
    private String name;
    private String location;
    private LocalTime openTime;
    private LocalTime closeTime;
    private boolean isOperating;
    private LocalDateTime createdAt;
}
