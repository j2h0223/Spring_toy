package com.toy.toycinema.search.controller;

import com.toy.toycinema.component.PagingComponent;

import com.toy.toycinema.dto.*;

import com.toy.toycinema.search.service.SearchService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("search")
public class SearchController {

    private final SearchService searchService;
    private final PagingComponent pagingComponent;

    public SearchController(SearchService searchService, PagingComponent pagingComponent) {
        this.searchService = searchService;
        this.pagingComponent = pagingComponent;
    }

    @RequestMapping("searchProcess")
    public String searchProcess(
            @ModelAttribute FilmSearchDto filmSearchDto,
            Model model
    ) {
//        System.out.println("filmSearchDto = " + filmSearchDto);
        filmSearchDto.setContentsCountInPage(8);
        int totalCountFilm = searchService.getCountSearchedFilm(filmSearchDto);
        model.addAllAttributes(pagingComponent.calcPaging(filmSearchDto, totalCountFilm));
        model.addAllAttributes(searchService.searchByText(filmSearchDto));

        return "/search/searchResultPage";
    }
}
