package com.toy.board.dto;


import lombok.Data;

import java.time.LocalDate;

@Data
public class ControllerMemberDto {
    private String accountName;
    private String password;
    private String nickname;
    private String email;
    private char gender;
    private LocalDate birth;
    private String phone;
}
