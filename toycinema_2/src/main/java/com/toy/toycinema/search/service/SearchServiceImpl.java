package com.toy.toycinema.search.service;

import com.toy.toycinema.dto.*;

import com.toy.toycinema.search.mapper.SearchSqlMapper;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SearchServiceImpl implements SearchService{


    private final SearchSqlMapper searchSqlMapper;

    public SearchServiceImpl(SearchSqlMapper searchSqlMapper) {
        this.searchSqlMapper = searchSqlMapper;
    }

    @Override
    public Map<String, Object> searchByText(FilmSearchDto filmSearchDto) {
        filmSearchDto.setPageIndex((filmSearchDto.getPageIndex() - 1) * filmSearchDto.getContentsCountInPage());

        Map<String, Object> map = new HashMap<>();
        searchFilmByText(map, filmSearchDto);
        searchPeopleByText(map, filmSearchDto);
        searchFilmByGenreText(map, filmSearchDto);
        map.put("searchText", filmSearchDto.getText());

        return map;
    }

    @Override
    public int getCountSearchedFilm(FilmSearchDto filmSearchDto) {
        return searchSqlMapper.countFilmSearched(filmSearchDto);
    }

    private void searchFilmByText(Map<String, Object> map, FilmSearchDto filmSearchDto) {
        List<FilmDto> searchedFilmDtoList = searchSqlMapper.selectFilmDtoByText(filmSearchDto);
        map.put("filmDtoList", searchedFilmDtoList);
    }

    private void searchPeopleByText(Map<String, Object> map, FilmSearchDto filmSearchDto) {
        List<PeopleDto> searchedPeopleDtoList = searchSqlMapper.selectPeopleDtoByText(filmSearchDto);
        map.put("searchedPeopleDtoList", searchedPeopleDtoList);
    }

    private void searchFilmByGenreText(Map<String, Object> map, FilmSearchDto filmSearchDto) {
        List<FilmDto> searchedFilmDtoByGenreList = searchSqlMapper.selectFilmDtoByGenreText(filmSearchDto);
        map.put("searchedFilmDtoByGenreList", searchedFilmDtoByGenreList);
    }
}
