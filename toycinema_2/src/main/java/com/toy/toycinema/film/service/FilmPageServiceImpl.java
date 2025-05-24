package com.toy.toycinema.film.service;

import com.toy.toycinema.dto.*;

import com.toy.toycinema.film.mapper.FilmSqlMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmPageServiceImpl implements FilmPageService{


    private final FilmSqlMapper filmSqlMapper;

    public FilmPageServiceImpl(FilmSqlMapper filmSqlMapper) {
        this.filmSqlMapper = filmSqlMapper;
    }

    @Override
    public List<FilmDto> getAllFilmList(FilmSearchDto filmSearchDto) {
        filmSearchDto.setPageIndex((filmSearchDto.getPageIndex() - 1) * filmSearchDto.getContentsCountInPage());
        return filmSqlMapper.selectAllFilm(filmSearchDto);
    }

    @Override
    public int getCountAllFilm(FilmSearchDto filmSearchDto) {
        return filmSqlMapper.countAllFilm(filmSearchDto);
    }
}
