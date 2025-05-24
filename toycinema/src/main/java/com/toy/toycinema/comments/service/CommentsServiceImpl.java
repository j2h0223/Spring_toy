package com.toy.toycinema.comments.service;

import com.toy.toycinema.dto.*;

import com.toy.toycinema.comments.mapper.CommentsSqlMapper;
import org.springframework.stereotype.Service;

@Service
public class CommentsServiceImpl implements CommentsService{
    private final CommentsSqlMapper commentsSqlMapper;

    public CommentsServiceImpl(CommentsSqlMapper commentsSqlMapper) {
        this.commentsSqlMapper = commentsSqlMapper;
    }

    @Override
    public void writeMemberCommentAboutFilm(MemberCommentsFilmDto memberCommentsFilmDto) {
        commentsSqlMapper.insertMemberCommentsFilm(memberCommentsFilmDto);
    }
}
