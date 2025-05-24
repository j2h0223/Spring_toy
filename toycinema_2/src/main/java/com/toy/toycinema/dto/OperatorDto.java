package com.toy.toycinema.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OperatorDto {
    private int id;
    private String accountName;
    private String password;
    private String name;
    private LocalDateTime createdAt;
}
