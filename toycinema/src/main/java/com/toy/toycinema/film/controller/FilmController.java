package com.toy.toycinema.film.controller;

import com.toy.toycinema.dto.*;


import com.toy.toycinema.component.PagingComponent;

import com.toy.toycinema.film.service.FilmInfoService;
import com.toy.toycinema.film.service.FilmPageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("film")
public class FilmController {


    private final FilmInfoService filmInfoService;
    private final FilmPageService filmPageService;
    private final PagingComponent pagingComponent;


    public FilmController(FilmInfoService filmInfoService, FilmPageService filmPageService, PagingComponent pagingComponent) {
        this.filmInfoService = filmInfoService;
        this.filmPageService = filmPageService;
        this.pagingComponent = pagingComponent;
    }

    @RequestMapping("filmDetailsPage/{filmId}")
    public String filmDetailsPage(
            @PathVariable("filmId") int filmId,
            @RequestParam(value = "comments", required = false) String comments,
            Model model
    ) {
        Map<String, Object> filmInfo = filmInfoService.getFilmDetails(filmId);

        model.addAttribute("filmInfo", filmInfo);

        if (comments != null){
            String string = "comments";
            model.addAttribute("string", string);
        }

        return "/film/filmDetailsPage";
    }

    @RequestMapping("filmPage")
    public String filmPage(
            @ModelAttribute FilmSearchDto filmSearchDto,
            Model model
    ) {

//        int startPage = ;
//        model.addAttribute("startPage", startPage);

//        int lastPage = (int) Math.ceil((double) totalCountFilm / filmSearchDto.getContentsCountInPage());
//        model.addAttribute("lastPage", lastPage);

//        int endPage = calcEndPage(filmSearchDto.getPageIndex(), lastPage, totalCountFilm);
//        model.addAttribute("endPage", endPage);

//        model.addAttribute("currentPage", filmSearchDto.getPageIndex());

        int totalCountFilm = filmPageService.getCountAllFilm(filmSearchDto);
        model.addAllAttributes(pagingComponent.calcPaging(filmSearchDto, totalCountFilm));

        List<FilmDto> filmDtoList = filmPageService.getAllFilmList(filmSearchDto);
        model.addAttribute("filmDtoList", filmDtoList);

//        int totalCountFilm = searchService.getCountSearchedFilm(filmSearchDto);
//        model.addAllAttributes(pagingComponent.calcPaging(filmSearchDto, totalCountFilm));
//        model.addAllAttributes(searchService.searchByText(filmSearchDto));


        return "/film/filmPage";
    }




}
