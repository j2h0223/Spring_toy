package com.toy.toycinema.member.mapper;

import com.toy.toycinema.dto.*;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Mapper
public interface MemberSqlMapper {
    void insertMember(MemberDto memberDto);

    MemberDto selectMemberByAccountNameAndId(
            @Param("accountName") String accountName,
            @Param("password") String password
    );

    MemberDto selectMemberDtoById(
            MemberDto memberDto
    );

    MemberDto selectCriticMemberDtoById(
            MemberDto memberDto
    );

    List<FilmReservationDto> selectFilmReservationListByMember(
            @Param("memberId") int MemberId,
            @Param("today") LocalDate today,
            @Param("time") LocalTime time
    );

    List<FilmReservationDto> selectAlreadyViewFilmReservationListByMember(
            @Param("memberId") int MemberId,
            @Param("today") LocalDate today,
            @Param("time") LocalTime time
    );

    MemberCommentsFilmDto selectMemberCommentsFilmDto(MemberCommentsFilmDto memberCommentsFilmDto);

    FilmReservationDto selectFilmReservationDtoByFilmReservation(FilmReservationDto filmReservationDto);

    void deleteFilmReservationByFilmReservation(FilmReservationDto filmReservationDto);

}
