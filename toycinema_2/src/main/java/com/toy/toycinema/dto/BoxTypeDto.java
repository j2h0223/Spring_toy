package com.toy.toycinema.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BoxTypeDto {
    private int id;
    private int boxId;
    private int typeId;
    private double price;
    private LocalDateTime createdAt;
}
