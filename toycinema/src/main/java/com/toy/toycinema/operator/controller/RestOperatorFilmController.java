package com.toy.toycinema.operator.controller;

import com.toy.toycinema.dto.FilmDto;
import com.toy.toycinema.operator.service.RestOperatorFilmService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;


@RestController
@RequestMapping("api/operator/filmList")
public class RestOperatorFilmController {


    private final RestOperatorFilmService restOperatorFilmService;

    public RestOperatorFilmController(RestOperatorFilmService restOperatorFilmService) {
        this.restOperatorFilmService = restOperatorFilmService;
    }

    @GetMapping("filmTotalList")
    public List<Map<String, Object>> filmTotalList() {

        return restOperatorFilmService.findFilmTotalList();
    }

    @GetMapping("detailInfoOfCertainFilm/{id}")
    public Map<String, Object> detailInfoOfCertainFilm(
            @ModelAttribute FilmDto filmDto
    ) {
        return restOperatorFilmService.findFilmDetailInfo(filmDto);
    }

    @GetMapping("audienceCountByGender/{id}")
    public Map<String, Object> audienceCountByGender(
            @ModelAttribute FilmDto filmDto
    ) {
        return restOperatorFilmService.findAudienceCountByGender(filmDto);
    }

    @GetMapping("audienceCountByDate/{id}")
    public List<Map<String, Object>> audienceCountByDate(
            @ModelAttribute FilmDto filmDto
    ) {
        return restOperatorFilmService.findAudienceCountByDate(filmDto);
    }
}
