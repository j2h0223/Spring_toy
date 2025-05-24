package com.toy.toycinema.comments.mapper;


import com.toy.toycinema.dto.*;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentsSqlMapper {

    void insertMemberCommentsFilm(MemberCommentsFilmDto memberCommentsFilmDto);

    List<MemberCommentsFilmDto> selectMemberCommentsFilmDtoByFilmId(FilmDto filmDto);
    List<MemberCommentsFilmDto> selectCriticMemberCommentsFilmDtoByFilmId(FilmDto filmDto);

    List<ReplyDto> selectAllReplyDto3Depth();
}
