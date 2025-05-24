package com.toy.board.dto;

import lombok.Builder;
import lombok.Data;


@Builder
@Data
public class ServiceMemberDto {
    private Integer memberId;
    private String accountName;
    private String password;
}
