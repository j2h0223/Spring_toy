package com.toy.toycinema.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class MemberDto {
    private int id;
    private String accountName;
    private String password;
    private String nickname;
    private String gender;
    private String email;
    private LocalDate birth;
    private String phone;
    private boolean isCritic;
    private boolean isActivated;
    private LocalDateTime createdAt;
}
