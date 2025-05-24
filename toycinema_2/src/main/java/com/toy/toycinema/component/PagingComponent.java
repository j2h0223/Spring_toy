package com.toy.toycinema.component;

import com.toy.toycinema.dto.*;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class PagingComponent {
//    private final FilmPageService filmPageService;

//    public PagingComponent(FilmPageService filmPageService) {
//        this.filmPageService = filmPageService;
//    }

    public Map<String, Object> calcPaging(FilmSearchDto filmSearchDto, int totalCountFilm ) {
        Map<String, Object> map = new HashMap<>();

//        int totalCountFilm = filmPageService.getCountAllFilm(filmSearchDto);
        map.put("totalCountFilm", totalCountFilm);

        int startPage = calcStartPage(filmSearchDto.getPageIndex(), totalCountFilm);
        map.put("startPage", startPage);

        int lastPage = (int) Math.ceil((double) totalCountFilm / filmSearchDto.getContentsCountInPage());
        map.put("lastPage", lastPage);

        int endPage = calcEndPage(filmSearchDto.getPageIndex(), lastPage, totalCountFilm);
        map.put("endPage", endPage);

        int currentPage = calcCurrentPage(filmSearchDto.getPageIndex(), startPage, lastPage);
        filmSearchDto.setPageIndex(currentPage);
        map.put("currentPage", currentPage);

        return map;
    }

    private int calcStartPage(int page, int totalCountFilm) {
        int startPage = ((page - 1) / 5) * 5 + 1;
//        if (totalCountFilm <= 0) {
//            startPage = 1;
//        }
        return totalCountFilm <= 0 ? 1 : startPage;
    }

    private int calcEndPage(int page, int lastPage, int totalCountFilm) {
        int endPage = (((page - 1) / 5) + 1) * 5 ;
//        if(endPage > lastPage){
//            endPage = lastPage;
//        }
//        if (totalCountFilm <= 0) {
//            endPage = 1;
//        }
//        return totalCountFilm <= 0 ? 1 : (endPage > lastPage) ? lastPage : endPage;
        return totalCountFilm <= 0 ? 1 : Math.min(endPage, lastPage);
    }

    private int calcCurrentPage(int page, int startPage, int lastPage) {
//        if (page < startPage) {
//            page = startPage;
//        }
//        if (page > lastPage) {
//            page = lastPage;
//        }
//        return page;

//        return (page < startPage) ? startPage : Math.min(page, lastPage);
        return Math.max(startPage, Math.min(page, lastPage));
    }

}
