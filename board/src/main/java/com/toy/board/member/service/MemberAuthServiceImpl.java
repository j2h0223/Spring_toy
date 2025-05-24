package com.toy.board.member.service;

import com.toy.board.dto.ServiceMemberDto;
import com.toy.board.dto.member.mapper.MemberSqlMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberAuthServiceImpl implements MemberAuthService{

    private final MemberSqlMapper memberSqlMapper;

    @Autowired
    public MemberAuthServiceImpl(MemberSqlMapper memberSqlMapper) {
        this.memberSqlMapper = memberSqlMapper;
    }

    @Override
    public ServiceMemberDto login(String accountName, String password) {
        ServiceMemberDto serviceMemberDto = memberSqlMapper.selectMemberByAccountName(accountName);
        if (serviceMemberDto == null) {
            throw new IllegalArgumentException("Id가 존재하지 않습니다");
//        } else if (password.equals(serviceMemberDto.getPassword())) {
        } else if (!serviceMemberDto.getPassword().equals(password)) {
            throw new IllegalArgumentException("password가 일치하지 않습니다");
        } else {
            return serviceMemberDto;
        }
    }


}
