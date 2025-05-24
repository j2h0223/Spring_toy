package com.toy.board.member.service;

import com.toy.board.dto.ServiceMemberDto;

public interface MemberAuthService {
    ServiceMemberDto login(String accountName, String password);
}
