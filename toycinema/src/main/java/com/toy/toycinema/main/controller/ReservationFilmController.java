package com.toy.toycinema.main.controller;

import com.toy.toycinema.dto.*;

import com.toy.toycinema.main.service.ReservationFilmService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalTime;

@Controller
@RequestMapping("main")
public class ReservationFilmController {

    private final ReservationFilmService reservationFilmService;

    public ReservationFilmController(ReservationFilmService reservationFilmService) {
        this.reservationFilmService = reservationFilmService;
    }

    @RequestMapping("reservationPage3443")
    public String reservationPage(
            @RequestParam(name = "filmName", required = false, defaultValue = "0") String filmName,
            @RequestParam(name = "filmId", required = false, defaultValue = "0") int filmId,
            @RequestParam(name = "theaterName", required = false, defaultValue = "0") String theaterName,
            @RequestParam(name = "theaterId", required = false, defaultValue = "0") int theaterId,
            @RequestParam(name = "date", required = false) LocalDate date,
            HttpSession session,
            Model model
    ) {

        SelectFilmReservationDto selectFilmReservationDto = new SelectFilmReservationDto();

        selectFilmReservationDto.setToday(LocalDate.now());

        System.out.println("filmName = " + filmName);
        System.out.println("filmId = " + filmId);
        System.out.println("theaterName = " + theaterName);
        System.out.println("theaterId = " + theaterId);
        System.out.println("date = " + date);

        System.out.println("controller end");
        System.out.println();

        if (filmId > 0 ){
            selectFilmReservationDto.setFilmId(filmId);
        }
        if (theaterId>0){
            selectFilmReservationDto.setTheaterId(theaterId);
        }
        if (date == null){
//            selectFilmReservationDto.setDate(LocalDate.now());
            selectFilmReservationDto.setToday(LocalDate.now());
            selectFilmReservationDto.setTime(LocalTime.now().withNano(0));
        }
        if (date != null) {
            selectFilmReservationDto.setDate(date);
        }


        model.addAllAttributes(reservationFilmService.getScreeningSchedule(selectFilmReservationDto));

//        model.addAttribute("filmIdParam", filmIdParam);
//        model.addAttribute("theaterIdParam", theaterIdParam);

        return "main/reservationPage";
    }

    @RequestMapping("reservationPage")
    public String tempRestReservationPage(
            HttpSession session,
            Model model
    ) {
        MemberDto memberDto = (MemberDto) session.getAttribute("memberLoginInfo");
        model.addAttribute("memberDto", memberDto);
        return "main/restReservationPage";
    }

}
