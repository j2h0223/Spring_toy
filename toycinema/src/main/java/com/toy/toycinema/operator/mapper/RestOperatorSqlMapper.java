package com.toy.toycinema.operator.mapper;

import com.toy.toycinema.dto.*;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.util.*;

@Mapper
public interface RestOperatorSqlMapper {

    // 영화 목록
    List<FilmDto> selectAllFilmListForRest();

    // 하나의 영화에 대한 정보 찾는 용도
    List<FilmYoutubeDto> selectFilmYoutubeListByFilmId(FilmYoutubeDto filmYoutubeDto);
    List<FilmPosterDto> selectFilmPosterListByFilmId(FilmPosterDto filmPosterDto);

    List<FilmDirectorPeopleDto> selectDirectorListByFilmId(FilmDirectorPeopleDto filmDirectorPeopleDto);
    List<FilmActorPeopleDto> selectActorListByFilmId(FilmActorPeopleDto filmActorPeopleDto);
    PeopleDto selectPeopleDtoById(PeopleDto peopleDto);

    List<FilmGenreDto> selectFilmGenreListByFilmId(FilmGenreDto filmGenreDto);
    GenreDto selectGenreDtoByGenreId(GenreDto genreDto);

    List<FilmTypeDto> selectFilmTypeListByFilmId(FilmTypeDto filmTypeDto);
    TypeDto selectTypeDtoByTypeID(TypeDto typeDto);

    List<FilmPlayingTableDto> selectFilmPlayingTableListByFilmID(FilmPlayingTableDto filmPlayingTableDto);
    List<TheaterDto> selectTheaterListByFilmId(FilmPlayingTableDto filmPlayingTableDto);

    List<BoxDto> selectBoxListByFilmId(
            @Param("filmId") int filmId,
            @Param("theaterId") int theaterId
    );
    List<TypeDto> selectTypeListByFilmId(BoxDto boxDto);

    BoxTypeDto selectBoxTypeDto(@Param("filmId") int filmId,
                                @Param("boxId") int boxId);


    // 통계
    int countAudienceByGender(
            @Param("filmDto") FilmDto filmDto,
            @Param("gender") String gender
    );

    List<DateDto> selectPlayingDateListByFilm(FilmDto filmDto);
    int countAudienceByDate(FilmPlayingTableDto filmPlayingTableDto);

}
