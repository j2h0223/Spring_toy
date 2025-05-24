package com.toy.toycinema.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BoxDto {
    private int id;
    private int theaterId;
    private String name;
    private String location;
    private int capacity;
    private boolean isOperation;
    private double price;
    private LocalDateTime createdAt;
}
