package com.toy.toycinema.dto;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MemberCommentsFilmDto {
    private int id;
    private String text;
    private int point;
    private int memberId;
    private int filmId;
    private int parentCommentsId;
    private boolean is_deleted;
    private LocalDateTime createdAt;
}
