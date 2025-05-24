package com.toy.toycinema.search.service;


import com.toy.toycinema.dto.FilmSearchDto;

import java.util.Map;

public interface SearchService {
    Map<String, Object> searchByText(FilmSearchDto filmSearchDto);

    int getCountSearchedFilm(FilmSearchDto filmSearchDto);
}
