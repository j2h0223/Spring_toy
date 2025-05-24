package com.toy.toycinema.member.controller;

import com.toy.toycinema.dto.FilmReservationDto;
import com.toy.toycinema.dto.MemberCommentsFilmDto;
import com.toy.toycinema.dto.MemberDto;
import com.toy.toycinema.member.service.RestMemberMyPageService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("member")
public class RestMemberMyPageController {

    private final RestMemberMyPageService restMemberMyPageService;

    public RestMemberMyPageController(RestMemberMyPageService restMemberMyPageService) {
        this.restMemberMyPageService = restMemberMyPageService;
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
            @ModelAttribute MemberCommentsFilmDto memberCommentsFilmDto
    ) {

        return Map.of("result", "success");
    }

    @PostMapping("updateComment")
    public Map<String, Object> updateComment(
            @ModelAttribute MemberCommentsFilmDto memberCommentsFilmDto
    ) {

        return Map.of("result", "success");
    }
}


