package com.toy.toycinema.film.mapper;

import com.toy.toycinema.dto.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FilmSqlMapper {

    FilmDto selectFilmDtoByFilmId(FilmDto filmDto);

    FilmDto selectFilmDtoByDirectorPeopleId(FilmDirectorPeopleDto filmDirectorPeopleDto);

    FilmDto selectFilmDtoByActorPeopleId(FilmActorPeopleDto filmActorPeopleDto);

    List<PeopleDto> selectPeopleDtoActorByFilmId(FilmDto filmDto);

    List<PeopleDto> selectPeopleDtoDirectorByFilmId(FilmDto filmDto);

    List<GenreDto> selectGenreDtoFilmGenreByFilmId(FilmDto filmDto);

    List<FilmDto> selectAllFilm(FilmSearchDto filmSearchDto);

    int countAllFilm(FilmSearchDto filmSearchDto);
}