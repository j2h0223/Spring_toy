package com.toy.toycinema.film.service;


import com.toy.toycinema.dto.*;


import java.util.List;

public interface FilmPageService
{
    List<FilmDto> getAllFilmList(FilmSearchDto filmSearchDto);

    int getCountAllFilm(FilmSearchDto filmSearchDto);
}
