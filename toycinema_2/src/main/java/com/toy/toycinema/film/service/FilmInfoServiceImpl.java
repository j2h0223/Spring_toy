package com.toy.toycinema.film.service;


import com.toy.toycinema.comments.mapper.CommentsSqlMapper;
import com.toy.toycinema.dto.*;

import com.toy.toycinema.film.mapper.FilmSqlMapper;
import com.toy.toycinema.member.mapper.MemberSqlMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FilmInfoServiceImpl implements FilmInfoService{


    private final FilmSqlMapper filmSqlMapper;
    private final CommentsSqlMapper commentsSqlMapper;
    private final MemberSqlMapper memberSqlMapper;

    public FilmInfoServiceImpl(FilmSqlMapper filmSqlMapper, CommentsSqlMapper commentsSqlMapper, MemberSqlMapper memberSqlMapper) {
        this.filmSqlMapper = filmSqlMapper;
        this.commentsSqlMapper = commentsSqlMapper;
        this.memberSqlMapper = memberSqlMapper;
    }

    @Override
    public Map<String, Object> getFilmDetails(int filmId) {
        Map<String, Object> filmDetailsMap = new HashMap<>();

        FilmDto filmDto = getFilmDto(filmId);
        List<PeopleDto> peopleDtoDirectorList = filmSqlMapper.selectPeopleDtoDirectorByFilmId(filmDto);
        List<PeopleDto> peopleDtoActorList = filmSqlMapper.selectPeopleDtoActorByFilmId(filmDto);
        List<GenreDto> genreDtoList = filmSqlMapper.selectGenreDtoFilmGenreByFilmId(filmDto);

        List<Map<String, Object>> userCommentList = getCommentAndMemberInfoList(filmId, false);
        List<Map<String, Object>> criticCommentList = getCommentAndMemberInfoList(filmId, true);


        filmDetailsMap.put("filmDto", filmDto);
        filmDetailsMap.put("peopleDtoDirectorList", peopleDtoDirectorList);
        filmDetailsMap.put("peopleDtoActorList", peopleDtoActorList);
        filmDetailsMap.put("genreDtoList", genreDtoList);
        filmDetailsMap.put("commentDtoAndMemberDtoMapList", userCommentList);
        filmDetailsMap.put("commentDtoAndCriticMemberDtoMapList", criticCommentList);


        return filmDetailsMap;
    }

    private FilmDto getFilmDto(int filmId) {
        FilmDto filmDtoForParam = new FilmDto();
        filmDtoForParam.setId(filmId);
        return filmSqlMapper.selectFilmDtoByFilmId(filmDtoForParam);
    }

    private List<Map<String, Object>> getCommentAndMemberInfoList(int filmId, boolean isCritic) {
        FilmDto filmDtoParam = new FilmDto();
        filmDtoParam.setId(filmId);
        List<MemberCommentsFilmDto> commentsFilmDtoList = commentsSqlMapper.selectMemberCommentsFilmDtoByFilmId(filmDtoParam);
        List<Map<String, Object>> commentAndMemberInfoList = new ArrayList<>();

        for (MemberCommentsFilmDto commentsFilmDto : commentsFilmDtoList) {
            MemberDto memberDto = getMemberDto(commentsFilmDto.getMemberId(), isCritic);
            if (memberDto != null) {
                Map<String, Object> commentInfo = new HashMap<>();
                commentInfo.put("memberDto", memberDto);
                commentInfo.put("memberCommentsFilmDto", commentsFilmDto);
                commentAndMemberInfoList.add(commentInfo);
            }
        }
        return commentAndMemberInfoList;
    }

    private MemberDto getMemberDto(int memberId, boolean isCritic) {
        MemberDto param = new MemberDto();
        param.setId(memberId);
        return isCritic ? memberSqlMapper.selectCriticMemberDtoById(param) : memberSqlMapper.selectMemberDtoById(param);
    }

}
