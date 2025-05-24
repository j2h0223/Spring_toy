package com.toy.toycinema.member.service;

import com.toy.toycinema.dto.*;

import com.toy.toycinema.member.mapper.MemberSqlMapper;
import org.springframework.stereotype.Service;

@Service
public class MemberAuthServiceImpl implements MemberAuthService {

    private final MemberSqlMapper memberSqlMapper;

    public MemberAuthServiceImpl(MemberSqlMapper memberSqlMapper) {
        this.memberSqlMapper = memberSqlMapper;
    }

    @Override
    public void registerMember(MemberDto memberDto) {
        memberSqlMapper.insertMember(memberDto);
    }

    @Override
    public MemberDto authenticationMember(String accountName, String password) {
        return memberSqlMapper.selectMemberByAccountNameAndId(accountName, password);

    }
}
