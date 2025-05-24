package com.toy.toycinema.main.controller;

import com.toy.toycinema.dto.*;

import com.toy.toycinema.main.service.MainPageService;
import com.toy.toycinema.main.service.ReservationFilmService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.reflect.Member;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("main")
public class MainPageController {

    private final MainPageService mainPageService;
    private final ReservationFilmService reservationFilmService;

    public MainPageController(MainPageService mainPageService, ReservationFilmService reservationFilmService) {
        this.mainPageService = mainPageService;
        this.reservationFilmService = reservationFilmService;
    }

    @RequestMapping("homePage")
    public String homepage(
            Model model
    ) {
        List<Map<String, Object>> filmDtoAndFilmPosterDtoList = mainPageService.getNowPlayingFilmDtoList();

        model.addAttribute("filmDtoAndFilmPosterDtoList", filmDtoAndFilmPosterDtoList);

        return "/main/homePage";
    }


    @RequestMapping("theater")
    public String tempRestReservationPage(
            HttpSession session,
            Model model
    ) {
        MemberDto memberDto = (MemberDto) session.getAttribute("memberLoginInfo");
        model.addAttribute("memberDto", memberDto);
        return "main/restReservationPage";
    }

    @RequestMapping("makeConfirm")
    public String makeConfirm(
            @ModelAttribute FilmPlayingTableDto filmPlayingTableDto,
            HttpSession session,
            Model model
    ) {
        MemberDto memberDto = (MemberDto) session.getAttribute("memberLoginInfo");
        model.addAttribute("memberDto", memberDto);
        model.addAttribute("filmPlayingTableDto", filmPlayingTableDto);
        return "/main/restReservationPayment";
    }

    @RequestMapping("paymentCheck")
    public String paymentCheck(
            @RequestParam("memberId") int memberId,
            @RequestParam("filmPlayingTableId") int filmPlayingTableId,
            @RequestParam("seatCount") int seatCount,
            HttpSession session,
            Model model
    ) {
        System.out.println("id = " + memberId);

        MemberDto memberDto = (MemberDto) session.getAttribute("memberLoginInfo");
        if (memberDto.getId() == memberId) {
            FilmReservationDto filmReservationDto = new FilmReservationDto();
            filmReservationDto.setMemberId(memberId);
            filmReservationDto.setFilmPlayingTableId(filmPlayingTableId);
            filmReservationDto.setCount(seatCount);

//            model.addAttribute("filmReservationDto", filmReservationDto);
            reservationFilmService.setReservation(filmReservationDto);

            return "redirect:/main/paymentCheckPage?id=" + filmReservationDto.getId();

        } else {
            model.addAttribute("filmReservationDto", "failure");
            return "/main/reservationFailure";
        }

//        model.addAttribute("filmPlayingTableDto", filmPlayingTableDto);
    }

    @RequestMapping("paymentCheckPage")
    public String paymentCheckPage(
            @RequestParam("id") int id,
            Model model
    ) {
        FilmReservationDto filmReservationDto = new FilmReservationDto();
        filmReservationDto.setId(id);
//
        model.addAttribute("filmReservationDto", filmReservationDto);
//        System.out.println("filmReservationDto = " + filmReservationDto);

        return "/main/paymentCheckPage";
    }

    @RequestMapping("reservationFailure")
    public String reservationFailure() {

        return "/main/reservationFailure";
    }
}
