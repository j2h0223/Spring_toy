package com.toy.toycinema.comments.controller;

import com.toy.toycinema.dto.*;

import com.toy.toycinema.comments.service.CommentsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("comments")
public class CommentsController {

    private final CommentsService commentsService;

    public CommentsController(CommentsService commentsService) {
        this.commentsService = commentsService;
    }

    @PostMapping("writeCommentsProcess")
    public String writeCommentsProcess(
            @ModelAttribute MemberCommentsFilmDto memberCommentsFilmDto
    ) {
//        System.out.println("memberCommentsFilmDto = " + memberCommentsFilmDto);
        commentsService.writeMemberCommentAboutFilm(memberCommentsFilmDto);

        return "redirect:/film/filmDetailsPage/" + memberCommentsFilmDto.getFilmId() + "?comments=ture";
    }

}
