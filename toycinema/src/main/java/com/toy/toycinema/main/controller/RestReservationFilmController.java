package com.toy.toycinema.main.controller;


import com.toy.toycinema.dto.FilmDto;
import com.toy.toycinema.dto.FilmPlayingTableDto;
import com.toy.toycinema.dto.SelectFilmReservationDto;
import com.toy.toycinema.dto.TheaterDto;
import com.toy.toycinema.main.service.RestReservationFilmService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;


@RestController
@RequestMapping("api/main")
public class RestReservationFilmController {

    private final RestReservationFilmService restReservationFilmService;

    public RestReservationFilmController(RestReservationFilmService restReservationFilmService) {
        this.restReservationFilmService = restReservationFilmService;
    }


    @GetMapping("restReservationPage")
    @ResponseBody
    public Map<String, Object> reservationPage(
            @ModelAttribute SelectFilmReservationDto selectFilmReservationDto
    ) {
//        System.out.println("selectFilmReservationDto = " + selectFilmReservationDto);
        selectFilmReservationDto.setToday(LocalDate.now());
//        System.out.println();
//        System.out.println("new");
//        System.out.println("selectFilmReservationDto = " + selectFilmReservationDto);

//        return Map.of();
        return restReservationFilmService.getFilmTheaterDateBoxTypeTime(selectFilmReservationDto);
    }

    @GetMapping("selectedFilmInfo")
    @ResponseBody
    public Map<String, Object> selectedFilmInfo(
            @ModelAttribute FilmDto filmDto
    ) {
        return restReservationFilmService.getSelectedFilmInfo(filmDto);
    }

    @GetMapping("selectedTheaterInfo")
    @ResponseBody
    public Map<String, Object> selectedTheaterInfo(
            @ModelAttribute TheaterDto theaterDto
    ) {
        return restReservationFilmService.getSelectedTheaterInfo(theaterDto);
    }

    @GetMapping("playingTable")
    @ResponseBody
    public List<Map<String, Object>> playingTableInfo(
            @ModelAttribute SelectFilmReservationDto selectFilmReservationDto
    ) {
//        selectFilmReservationDto.setDate(LocalDate.now());/
        if (selectFilmReservationDto.getDate() == null) {
            return List.of();
        }

//        System.out.println("selectFilmReservationDto = " + selectFilmReservationDto);
        return restReservationFilmService.getPlayingTableInfo(selectFilmReservationDto);
    }

    @GetMapping("selectedBoxAndTypeInfo")
    public Map<String, Object> selectedBoxAndTypeInfo(
            @ModelAttribute FilmPlayingTableDto filmPlayingTableDto
    ) {

        return restReservationFilmService.getBoxAndTypeInfo(filmPlayingTableDto);
    }


    @GetMapping("playingTableInfoCanvas")
    public Map<String, Object> playingTableInfoCanvas(
            @ModelAttribute FilmPlayingTableDto filmPlayingTableDto
    ) {
        return restReservationFilmService.getPlayingTableInfoCanvas(filmPlayingTableDto);
    }

    @GetMapping("filmPlayingTableDtoForPrice")
    public Map<String, Object> filmPlayingTableDtoForPrice(
            @ModelAttribute FilmPlayingTableDto param
    ) {
        FilmPlayingTableDto filmPlayingTableDto = restReservationFilmService.getFilmPlayingTableDto(param);
        return Map.of("filmPlayingTableDto", filmPlayingTableDto);
    }
}
