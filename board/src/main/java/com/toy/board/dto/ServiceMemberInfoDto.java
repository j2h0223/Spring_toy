package com.toy.board.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder
@Data
public class ServiceMemberInfoDto {
    private int memberId;
    private String nickname;
    private String email;
    private char gender;
    private LocalDate birth;
    private String phone;
}
