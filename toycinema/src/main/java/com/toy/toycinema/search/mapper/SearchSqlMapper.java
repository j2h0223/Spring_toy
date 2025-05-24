package com.toy.toycinema.search.mapper;

import com.toy.toycinema.dto.*;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SearchSqlMapper {
    List<FilmDto> selectFilmDtoByText(FilmSearchDto filmSearchDto);
    List<PeopleDto> selectPeopleDtoByText(FilmSearchDto filmSearchDto);
    List<FilmDto> selectFilmDtoByGenreText(FilmSearchDto filmSearchDto);

    int countFilmSearched(FilmSearchDto filmSearchDto);
}
