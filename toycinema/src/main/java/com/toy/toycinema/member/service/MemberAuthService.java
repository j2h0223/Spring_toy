package com.toy.toycinema.member.service;

import com.toy.toycinema.dto.*;


public interface MemberAuthService {
    void registerMember(MemberDto memberDto);
    MemberDto authenticationMember(String accountName, String password);
}
