package com.toy.toycinema.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ReplyDto {
    private int id;//23
    private String text;//23
    private int point;//123
    private int memberId;//23
    private int filmId;//23
    private int parentCommentsId;
    private boolean is_deleted;
    private LocalDateTime createdAt;//23

    List<ReplyDto> childReplyDtoList;
}
