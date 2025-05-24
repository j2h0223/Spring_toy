package com.toy.toycinema.dto;

import lombok.Data;

@Data
public class FilmSearchDto {
    private int pageIndex = 1;
    private int contentsCountInPage = 16;
    private String text;
}
