package com.toy.toycinema.operator.service;

import com.toy.toycinema.dto.*;

import com.toy.toycinema.operator.mapper.RestOperatorSqlMapper;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RestOperatorFilmServiceImpl implements RestOperatorFilmService {
    private final RestOperatorSqlMapper restOperatorSqlMapper;

    public RestOperatorFilmServiceImpl(RestOperatorSqlMapper restOperatorSqlMapper) {
        this.restOperatorSqlMapper = restOperatorSqlMapper;
    }

    @Override
    public List<Map<String, Object>> findFilmTotalList() {
        List<FilmDto> filmDtoList = restOperatorSqlMapper.selectAllFilmListForRest();
        return filmBasicInfo(filmDtoList);
    }

    @Override
    public Map<String, Object> findFilmDetailInfo(FilmDto filmDto) {
        return filmDetailInfo(filmDto);
    }

    @Override
    public Map<String, Object> findAudienceCountByGender(FilmDto filmDto) {
        Map<String, Object> map = new HashMap<>();
        map.put("M", restOperatorSqlMapper.countAudienceByGender(filmDto, "M"));
        map.put("F", restOperatorSqlMapper.countAudienceByGender(filmDto, "F"));
        return map;
    }

    @Override
    public List<Map<String, Object>> findAudienceCountByDate(FilmDto filmDto) {
        List<Map<String, Object>> list = new ArrayList<>();

        List<DateDto> dateDtoList = restOperatorSqlMapper.selectPlayingDateListByFilm(filmDto);

        for (DateDto dateDto : dateDtoList) {
            Map<String, Object> map = new HashMap<>();

            FilmPlayingTableDto filmPlayingTableDto = new FilmPlayingTableDto();
            filmPlayingTableDto.setFilmId(filmDto.getId());
            filmPlayingTableDto.setDate(dateDto.getDate());

            System.out.println("filmPlayingTableDto = " + filmPlayingTableDto);
            int countByDate = restOperatorSqlMapper.countAudienceByDate(filmPlayingTableDto);

            map.put("dateDto", dateDto.getDate());
            map.put("countByDate", countByDate);
            list.add(map);
        }

        return list;
    }

    private Map<String, Object> filmDetailInfo(FilmDto filmDto) {
        Map<String, Object> map = new HashMap<>();

        int filmId = filmDto.getId();

        FilmYoutubeDto filmYoutubeDto = new FilmYoutubeDto();
        filmYoutubeDto.setFilmId(filmId);
        List<FilmYoutubeDto> filmYoutubeDtoList = restOperatorSqlMapper.selectFilmYoutubeListByFilmId(filmYoutubeDto);

        FilmPosterDto filmPosterDto = new FilmPosterDto();
        filmPosterDto.setFilmId(filmId);
        List<FilmPosterDto> filmPosterDtoList = restOperatorSqlMapper.selectFilmPosterListByFilmId(filmPosterDto);

        FilmDirectorPeopleDto filmDirectorPeopleDto = new FilmDirectorPeopleDto();
        filmDirectorPeopleDto.setFilmId(filmId);
        List<FilmDirectorPeopleDto> filmDirectorPeopleDtoList = restOperatorSqlMapper.selectDirectorListByFilmId(filmDirectorPeopleDto);
        List<PeopleDto> filmDirectorDtoList = new ArrayList<>();
        for (FilmDirectorPeopleDto searchedFilmDirectorPeopleDto : filmDirectorPeopleDtoList) {
            PeopleDto directorDto = new PeopleDto();
            directorDto.setId(searchedFilmDirectorPeopleDto.getPeopleId());
            directorDto = restOperatorSqlMapper.selectPeopleDtoById(directorDto);
            filmDirectorDtoList.add(directorDto);
        }

        FilmActorPeopleDto filmActorPeopleDto = new FilmActorPeopleDto();
        filmActorPeopleDto.setFilmId(filmId);
        List<FilmActorPeopleDto> filmActorPeopleDtoList = restOperatorSqlMapper.selectActorListByFilmId(filmActorPeopleDto);
        List<PeopleDto> filmActorDtoList = new ArrayList<>();
        for (FilmActorPeopleDto searchedFilmActorPeopleDto : filmActorPeopleDtoList) {
            PeopleDto directorDto = new PeopleDto();
            directorDto.setId(searchedFilmActorPeopleDto.getPeopleId());
            directorDto = restOperatorSqlMapper.selectPeopleDtoById(directorDto);
            filmActorDtoList.add(directorDto);
        }

        FilmGenreDto filmGenreDto = new FilmGenreDto();
        filmGenreDto.setFilmId(filmId);
        List<FilmGenreDto> filmGenreDtoList = restOperatorSqlMapper.selectFilmGenreListByFilmId(filmGenreDto);
        List<GenreDto> genreDtoList = new ArrayList<>();
        for (FilmGenreDto searchedFilmGenreDto : filmGenreDtoList) {
            GenreDto genreDto = new GenreDto();
            genreDto.setId(searchedFilmGenreDto.getGenreId());
            genreDto = restOperatorSqlMapper.selectGenreDtoByGenreId(genreDto);
            genreDtoList.add(genreDto);
        }

        FilmTypeDto filmTypeDto = new FilmTypeDto();
        filmTypeDto.setFilmId(filmId);
        List<FilmTypeDto> filmTypeDtoList = restOperatorSqlMapper.selectFilmTypeListByFilmId(filmTypeDto);
        List<TypeDto> typeDtoList = new ArrayList<>();
        for (FilmTypeDto searchedFilmTypeDto : filmTypeDtoList) {
            TypeDto typeDto = new TypeDto();
            typeDto.setId(searchedFilmTypeDto.getTypeId());
            typeDto = restOperatorSqlMapper.selectTypeDtoByTypeID(typeDto);
            typeDtoList.add(typeDto);
        }

        map.put("filmYoutubeDtoList", filmYoutubeDtoList);
        map.put("filmPosterDtoList", filmPosterDtoList);
        map.put("filmDirectorDtoList", filmDirectorDtoList);
        map.put("filmActorDtoList", filmActorDtoList);
        map.put("genreDtoList", genreDtoList);
        map.put("typeDtoList", typeDtoList);

        return map;
    }


    private List<Map<String, Object>> filmBasicInfo(List<FilmDto> filmDtoList) {
        List<Map<String, Object>> list = new ArrayList<>();

        for (FilmDto filmDto : filmDtoList) {
            Map<String, Object> map = new HashMap<>();
            map.put("filmDto", filmDto);
            list.add(map);
        }
        return list;
    }
}
