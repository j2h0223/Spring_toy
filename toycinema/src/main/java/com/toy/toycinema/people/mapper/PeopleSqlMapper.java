package com.toy.toycinema.people.mapper;

import com.toy.toycinema.dto.*;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface PeopleSqlMapper {

    PeopleDto selectPeopleDtoByPeopleId(PeopleDto peopleDto);

    List<FilmDirectorPeopleDto> selectFilmDirectorPeopleDtoByPeopleId(PeopleDto peopleDto);

    List<FilmActorPeopleDto> selectFilmActorPeopleDtoByPeopleId(PeopleDto peopleDto);

}
