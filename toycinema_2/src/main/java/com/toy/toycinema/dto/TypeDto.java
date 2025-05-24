package com.toy.toycinema.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TypeDto {
    private int id;
    private String typeName;
    private double price;
    private LocalDateTime createdAt;
}
