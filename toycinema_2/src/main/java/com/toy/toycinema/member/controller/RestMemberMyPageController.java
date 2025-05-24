package com.toy.toycinema.member.controller;

import com.toy.toycinema.comments.service.CommentsService;
import com.toy.toycinema.dto.FilmReservationDto;
import com.toy.toycinema.dto.MemberCommentsFilmDto;
import com.toy.toycinema.dto.MemberDto;
import com.toy.toycinema.member.service.RestMemberMyPageService;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("member")
public class RestMemberMyPageController {

    private final RestMemberMyPageService restMemberMyPageService;
    private final CommentsService commentsService;

    public RestMemberMyPageController(RestMemberMyPageService restMemberMyPageService, CommentsService commentsService) {
        this.restMemberMyPageService = restMemberMyPageService;
        this.commentsService = commentsService;
    }


    @GetMapping("reservationCheck")
    public List<Map<String, Object>> reservationCheck(
            @ModelAttribute MemberDto memberDto
    ) {
        return restMemberMyPageService.getReservationInfo(memberDto);
    }

    @GetMapping("alreadyViewCheck")
    public List<Map<String, Object>> alreadyViewCheck(
            @ModelAttribute MemberDto memberDto
    ) {
        return restMemberMyPageService.getAlreadyViewInfo(memberDto);
    }

    @GetMapping("reviewedCheck")
    public List<Map<String, Object>> reviewedCheck(
            @ModelAttribute MemberDto memberDto
    ) {
        return restMemberMyPageService.getReviewedInfo(memberDto);
    }

    @GetMapping("paymentCheckPage")
    public Map<String, Object> paymentCheckPage(
            @ModelAttribute FilmReservationDto filmReservationDto
    ) {
        return restMemberMyPageService.getPaymentCheck(filmReservationDto);
    }

    @GetMapping("cancelReservation")
    public Map<String, Object> cancelReservation(
            @ModelAttribute FilmReservationDto filmReservationDto
    ) {

        System.out.println("filmReservationDto = " + filmReservationDto);
        restMemberMyPageService.removeReservation(filmReservationDto);

        return Map.of("result", "success");
    }

    @PostMapping("writeComment")
    public Map<String, Object> writeComment(
            @RequestBody MemberCommentsFilmDto memberCommentsFilmDto,
            HttpSession session
    ) {

        MemberDto memberDto = (MemberDto) session.getAttribute("memberLoginInfo");
//        System.out.println("memberDto = " + memberDto);
//        System.out.println("writeComment memberCommentsFilmDto = " + memberCommentsFilmDto);
//        if (memberDto.getId() == memberCommentsFilmDto.getMemberId()) {
//            System.out.println("writeComment memberCommentsFilmDto = " + memberCommentsFilmDto);
         memberCommentsFilmDto.setMemberId(memberDto.getId());
            commentsService.writeMemberCommentAboutFilm(memberCommentsFilmDto);
//        } else {
//            System.out.println("342");
//        }

        return Map.of("result", "success");
    }

    @PostMapping("updateComment")
    public Map<String, Object> updateComment(
            @RequestBody MemberCommentsFilmDto memberCommentsFilmDto,
            HttpSession session
    ) {
        commentsService.renewMemberCommentFilm(memberCommentsFilmDto);

        return Map.of("result", "success");
    }
}


