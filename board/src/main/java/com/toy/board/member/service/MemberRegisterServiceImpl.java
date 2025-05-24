package com.toy.board.member.service;

import com.toy.board.dto.ControllerMemberDto;
import com.toy.board.dto.ServiceMemberDto;
import com.toy.board.dto.ServiceMemberInfoDto;
import com.toy.board.dto.member.mapper.MemberSqlMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberRegisterServiceImpl implements MemberRegisterService{

    private final MemberSqlMapper memberSqlMapper;

    @Autowired
    public MemberRegisterServiceImpl(MemberSqlMapper memberSqlMapper) {
        this.memberSqlMapper = memberSqlMapper;
    }

    @Transactional
    @Override
    public void registerMember(ControllerMemberDto controllerMemberDto) {
        ServiceMemberDto serviceMemberDto = convertMemberDto(controllerMemberDto);
        memberSqlMapper.insertMember(serviceMemberDto);

        // 2. insert 후에 memberId를 가져옴
        Integer generatedMemberId = serviceMemberDto.getMemberId();
        if (generatedMemberId == null) {
            throw new IllegalStateException("회원가입에 실패했습니다: memberId 생성 실패");
        }

        // 3. memberId를 infoDto에 넣음
        ServiceMemberInfoDto infoDto = convertToMemberInfoDto(controllerMemberDto, generatedMemberId);
        memberSqlMapper.insertMemberInfo(infoDto);
    }

    private ServiceMemberDto convertMemberDto(ControllerMemberDto controllerMemberDto) {
        return ServiceMemberDto.builder()
                .accountName(controllerMemberDto.getAccountName())
                .password(controllerMemberDto.getPassword())
                .build();
    }

    private ServiceMemberInfoDto convertToMemberInfoDto(ControllerMemberDto controllerMemberDto, int memberId){
        return ServiceMemberInfoDto.builder()
                .memberId(memberId)
                .nickname(controllerMemberDto.getNickname())
                .email(controllerMemberDto.getEmail())
                .gender(controllerMemberDto.getGender())
                .birth(controllerMemberDto.getBirth())
                .phone(controllerMemberDto.getPhone())
                .build();
    }
}
