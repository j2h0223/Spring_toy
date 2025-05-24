package com.toy.board.dto.member.mapper;

import com.toy.board.dto.ServiceMemberDto;
import com.toy.board.dto.ServiceMemberInfoDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberSqlMapper {

    void insertMember(ServiceMemberDto serviceMemberDto);

    void insertMemberInfo(ServiceMemberInfoDto serviceMemberInfoDto);

    ServiceMemberDto selectMemberByAccountName(String accountName);
}
