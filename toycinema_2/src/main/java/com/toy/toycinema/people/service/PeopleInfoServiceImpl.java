package com.toy.toycinema.people.service;

import com.toy.toycinema.dto.*;

import com.toy.toycinema.film.mapper.FilmSqlMapper;
import com.toy.toycinema.people.mapper.PeopleSqlMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PeopleInfoServiceImpl implements PeopleInfoService {


    private final PeopleSqlMapper peopleSqlMapper;
    private final FilmSqlMapper filmSqlMapper;

    public PeopleInfoServiceImpl(PeopleSqlMapper peopleSqlMapper, FilmSqlMapper filmSqlMapper) {
        this.peopleSqlMapper = peopleSqlMapper;
        this.filmSqlMapper = filmSqlMapper;
    }

    @Override
    public Map<String, Object> getPeopleFilm(PeopleDto param) {
        Map<String, Object> peopleFilmInfoMap = new HashMap<>();

        PeopleDto peopleDto = peopleSqlMapper.selectPeopleDtoByPeopleId(param);
        peopleFilmInfoMap.put("peopleDto", peopleDto);

        List<FilmDirectorPeopleDto> filmDirectorPeopleDtoList = peopleSqlMapper.selectFilmDirectorPeopleDtoByPeopleId(param);
        List<FilmDto> directFilmDtoList = new ArrayList<>();
        for (FilmDirectorPeopleDto filmDirectorPeopleDto : filmDirectorPeopleDtoList) {
            FilmDto filmDto = filmSqlMapper.selectFilmDtoByDirectorPeopleId(filmDirectorPeopleDto);
            directFilmDtoList.add(filmDto);
        }
        peopleFilmInfoMap.put("directFilmDtoList", directFilmDtoList);

        List<FilmActorPeopleDto> filmActorPeopleDtoList = peopleSqlMapper.selectFilmActorPeopleDtoByPeopleId(param);
        List<FilmDto> actFilmDtoList = new ArrayList<>();
        for (FilmActorPeopleDto filmActorPeopleDto : filmActorPeopleDtoList) {
            FilmDto filmDto = filmSqlMapper.selectFilmDtoByActorPeopleId(filmActorPeopleDto);
            actFilmDtoList.add(filmDto);
        }
        peopleFilmInfoMap.put("actFilmDtoList", actFilmDtoList);

        return peopleFilmInfoMap;
    }
}
