package com.toy.toycinema.member.service;

import com.toy.toycinema.dto.*;
import com.toy.toycinema.main.mapper.RestReservationFilmMapper;
import com.toy.toycinema.member.mapper.MemberSqlMapper;
import org.springframework.stereotype.Service;

import java.lang.reflect.Member;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RestMemberMyPageServiceImpl implements RestMemberMyPageService {
    private final MemberSqlMapper memberSqlMapper;
    private final RestReservationFilmMapper restReservationFilmMapper;

    public RestMemberMyPageServiceImpl(MemberSqlMapper memberSqlMapper, RestReservationFilmMapper restReservationFilmMapper) {
        this.memberSqlMapper = memberSqlMapper;
        this.restReservationFilmMapper = restReservationFilmMapper;
    }

    @Override
    public void removeReservation(FilmReservationDto filmReservationDto) {
        memberSqlMapper.deleteFilmReservationByFilmReservation(filmReservationDto);
    }

    @Override
    public Map<String, Object> getPaymentCheck(FilmReservationDto param) {
        Map<String, Object> map = new HashMap<>();


        FilmReservationDto filmReservationDto = new FilmReservationDto();
        filmReservationDto.setId(param.getId());
        filmReservationDto = memberSqlMapper.selectFilmReservationDtoByFilmReservation(param);
//        System.out.println("filmReservationDto = " + filmReservationDto);

        FilmPlayingTableDto filmPlayingTableDto = new FilmPlayingTableDto();
        filmPlayingTableDto.setId(filmReservationDto.getFilmPlayingTableId());
        filmPlayingTableDto = restReservationFilmMapper.selectFilmPlayingTableDtoByFilmPlayingTable(filmPlayingTableDto);

        FilmDto filmDtoParam = new FilmDto();
        filmDtoParam.setId(filmPlayingTableDto.getFilmId());
        FilmDto filmDto = restReservationFilmMapper.selectFilmDtoByFilm(filmDtoParam);

        TypeDto typeDto = restReservationFilmMapper.selectTypeDtoByFilmPlayingTable(filmPlayingTableDto);
        BoxDto boxDto = restReservationFilmMapper.selectBoxDtoByFilmPlayingTable(filmPlayingTableDto);
        TheaterDto theaterDto = restReservationFilmMapper.selectTheaterDtoByFilmPlayingTable(filmPlayingTableDto);

        map.put("filmReservationDto", filmReservationDto);
        map.put("filmPlayingTableDto", filmPlayingTableDto);
        map.put("filmDto", filmDto);
        map.put("typeDto", typeDto);
        map.put("boxDto", boxDto);
        map.put("theaterDto", theaterDto);

        return map;
    }

    @Override
    public List<Map<String, Object>> getReviewedInfo(MemberDto param) {
        List<Map<String, Object>> list = new ArrayList<>();

        LocalDate today = LocalDate.now();
        LocalTime time = LocalTime.now();

        MemberDto memberDto = memberSqlMapper.selectMemberDtoById(param);

        List<FilmReservationDto> filmReservationDtoList = memberSqlMapper.selectAlreadyViewFilmReservationListByMember(param.getId(), today, time);
        for (FilmReservationDto filmReservationDto : filmReservationDtoList) {
            Map<String, Object> map = new HashMap<>();

            FilmPlayingTableDto filmPlayingTableDtoForSearch = new FilmPlayingTableDto();
            filmPlayingTableDtoForSearch.setId(filmReservationDto.getFilmPlayingTableId());
            FilmPlayingTableDto filmPlayingTableDto = restReservationFilmMapper.selectFilmPlayingTableDtoByFilmPlayingTable(filmPlayingTableDtoForSearch);

            FilmDto filmDtoParam = new FilmDto();
            filmDtoParam.setId(filmPlayingTableDto.getFilmId());
            FilmDto filmDto = restReservationFilmMapper.selectFilmDtoByFilm(filmDtoParam);

            MemberCommentsFilmDto memberCommentsFilmDtoForSearch = new MemberCommentsFilmDto();
            memberCommentsFilmDtoForSearch.setFilmId(filmDto.getId());
            memberCommentsFilmDtoForSearch.setMemberId(memberDto.getId());

            MemberCommentsFilmDto memberCommentsFilmDto = memberSqlMapper.selectMemberCommentsFilmDto(memberCommentsFilmDtoForSearch);
            if (memberCommentsFilmDto == null) {
                continue;
            }

            TypeDto typeDto = restReservationFilmMapper.selectTypeDtoByFilmPlayingTable(filmPlayingTableDto);
            BoxDto boxDto = restReservationFilmMapper.selectBoxDtoByFilmPlayingTable(filmPlayingTableDto);
            TheaterDto theaterDto = restReservationFilmMapper.selectTheaterDtoByFilmPlayingTable(filmPlayingTableDto);

//            System.out.println();
//            System.out.println("이미본거");
//            System.out.println("filmReservationDto = " + filmPlayingTableDto);
//            System.out.println("filmPlayingTableDto = " + filmPlayingTableDto);

            map.put("memberDto", memberDto);
            map.put("memberCommentsFilmDto", memberCommentsFilmDto);
            map.put("filmReservationDto", filmReservationDto);
            map.put("filmPlayingTableDto", filmPlayingTableDto);
            map.put("filmDto", filmDto);
            map.put("typeDto", typeDto);
            map.put("boxDto", boxDto);
            map.put("theaterDto", theaterDto);
            list.add(map);
        }

        return list;
    }

    @Override
    public List<Map<String, Object>> getAlreadyViewInfo(MemberDto param) {
        List<Map<String, Object>> list = new ArrayList<>();

        LocalDate today = LocalDate.now();
        LocalTime time = LocalTime.now();

        MemberDto memberDto = memberSqlMapper.selectMemberDtoById(param);

        List<FilmReservationDto> filmReservationDtoList = memberSqlMapper.selectAlreadyViewFilmReservationListByMember(param.getId(), today, time);
        for (FilmReservationDto filmReservationDto : filmReservationDtoList) {
            Map<String, Object> map = new HashMap<>();

            FilmPlayingTableDto filmPlayingTableDtoForSearch = new FilmPlayingTableDto();
            filmPlayingTableDtoForSearch.setId(filmReservationDto.getFilmPlayingTableId());
            FilmPlayingTableDto filmPlayingTableDto = restReservationFilmMapper.selectFilmPlayingTableDtoByFilmPlayingTable(filmPlayingTableDtoForSearch);

            FilmDto filmDtoParam = new FilmDto();
            filmDtoParam.setId(filmPlayingTableDto.getFilmId());
            FilmDto filmDto = restReservationFilmMapper.selectFilmDtoByFilm(filmDtoParam);

            MemberCommentsFilmDto memberCommentsFilmDtoForSearch = new MemberCommentsFilmDto();
            memberCommentsFilmDtoForSearch.setFilmId(filmDto.getId());
            memberCommentsFilmDtoForSearch.setMemberId(memberDto.getId());

            MemberCommentsFilmDto memberCommentsFilmDto = memberSqlMapper.selectMemberCommentsFilmDto(memberCommentsFilmDtoForSearch);

            TypeDto typeDto = restReservationFilmMapper.selectTypeDtoByFilmPlayingTable(filmPlayingTableDto);
            BoxDto boxDto = restReservationFilmMapper.selectBoxDtoByFilmPlayingTable(filmPlayingTableDto);
            TheaterDto theaterDto = restReservationFilmMapper.selectTheaterDtoByFilmPlayingTable(filmPlayingTableDto);

//            System.out.println();
//            System.out.println("이미본거");
//            System.out.println("filmReservationDto = " + filmPlayingTableDto);
//            System.out.println("filmPlayingTableDto = " + filmPlayingTableDto);
            map.put("memberDto", memberDto);
            map.put("memberCommentsFilmDto", memberCommentsFilmDto);
            map.put("filmReservationDto", filmReservationDto);
            map.put("filmPlayingTableDto", filmPlayingTableDto);
            map.put("filmDto", filmDto);
            map.put("typeDto", typeDto);
            map.put("boxDto", boxDto);
            map.put("theaterDto", theaterDto);
            list.add(map);
        }

        return list;
    }

    @Override
    public List<Map<String, Object>> getReservationInfo(MemberDto memberDto) {
        List<Map<String, Object>> list = new ArrayList<>();

        LocalDate today = LocalDate.now();
        LocalTime time = LocalTime.now();

        List<FilmReservationDto> filmReservationDtoList = memberSqlMapper.selectFilmReservationListByMember(memberDto.getId(), today, time);
        for (FilmReservationDto filmReservationDto : filmReservationDtoList) {
            Map<String, Object> map = new HashMap<>();

            FilmPlayingTableDto filmPlayingTableDtoForSearch = new FilmPlayingTableDto();
            filmPlayingTableDtoForSearch.setId(filmReservationDto.getFilmPlayingTableId());
            FilmPlayingTableDto filmPlayingTableDto = restReservationFilmMapper.selectFilmPlayingTableDtoByFilmPlayingTable(filmPlayingTableDtoForSearch);

            FilmDto filmDtoParam = new FilmDto();
            filmDtoParam.setId(filmPlayingTableDto.getFilmId());
            FilmDto filmDto = restReservationFilmMapper.selectFilmDtoByFilm(filmDtoParam);

            TypeDto typeDto = restReservationFilmMapper.selectTypeDtoByFilmPlayingTable(filmPlayingTableDto);
            BoxDto boxDto = restReservationFilmMapper.selectBoxDtoByFilmPlayingTable(filmPlayingTableDto);
            TheaterDto theaterDto = restReservationFilmMapper.selectTheaterDtoByFilmPlayingTable(filmPlayingTableDto);

            System.out.println();
            System.out.println("예매중");
            System.out.println("filmReservationDto = " + filmPlayingTableDto);
            System.out.println("filmPlayingTableDto = " + filmPlayingTableDto);

            map.put("filmReservationDto", filmReservationDto);
            map.put("filmPlayingTableDto", filmPlayingTableDto);
            map.put("filmDto", filmDto);
            map.put("typeDto", typeDto);
            map.put("boxDto", boxDto);
            map.put("theaterDto", theaterDto);
            list.add(map);
        }

        return list;
    }
}
