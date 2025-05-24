package com.toy.toycinema.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class RegisterBoxDto {
    private int id;
    private int theaterId;
    private String name;
    private String location;
    private int capacity;
    private int countType;
    private List<TypeDto> typeDtoList = new ArrayList<>();
}
