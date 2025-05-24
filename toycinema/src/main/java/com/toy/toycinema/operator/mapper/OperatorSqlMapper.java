package com.toy.toycinema.operator.mapper;

import com.toy.toycinema.dto.*;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OperatorSqlMapper {

    //    관리자 등록
    void insertOperator(OperatorDto operatorDto);

    OperatorDto selectOperatorForAuth(OperatorDto operatorDto);

    //  영화등록
    void insertFilmBasicInfo(RegisterFilmBasicInfoDto registerFilmBasicInfoDto);


    void insertFilmImage(FilmImageDto filmImageDto);

    void insertFilmUrlYoutube(
            @Param("urlYoutube") String urlYoutube,
            @Param("filmId") int filmId
    );


    PeopleDto selectPeopleDto(PeopleDto peopleDto);

    void insertFilmDirectorPeople(FilmPeopleDto FilmPeopleDto);

    void insertFilmActorPeople(FilmPeopleDto FilmPeopleDto);

    GenreDto selectGenreDto(GenreDto genreDto);

    void insertGenre(GenreDto genreDto);

    void insertFilmGenre(FilmGenreDto filmGenreDto);

    void insertPeople(PeopleDto peopleDto);

    TypeDto selectTypeDtoByTypeName(TypeDto typeDto);
    TypeDto selectTypeDtoByTypeId(TypeDto typeDto);

    void insertType(TypeDto typeDto);
    void insertTypeReturnID(TypeDto typeDto);

    void insertFilmType(FilmTypeDto filmTypeDto);
    FilmTypeDto selectFilmType(FilmTypeDto filmTypeDto);
    List<FilmTypeDto> selectAllFilmTypeByFilmId(FilmTypeDto filmTypeDto);

    void updateFilmUrlMain(@Param("urlMain") String urlMain, @Param("id") int id);

    void insertFilmPoster(FilmPosterDto filmPosterDto);

    // 극장 등록
    void insertTheater(TheaterDto theaterDto);
    TheaterDto selectTheaterDtoByTheaterID(TheaterDto theaterDto);
    List<TheaterDto> selectAllTheater();

    void insertBox(RegisterBoxDto registerBoxDto);
    BoxDto selectBoxDtoByBoxId(BoxDto boxDto);
    List<BoxDto> selectAllBox();


    void insertBoxType(BoxTypeDto boxTypeDto);
    BoxTypeDto selectBoxTypeDtoByBoxIdAndTypeId(BoxTypeDto boxTypeDto);
    List<BoxTypeDto> selectAllBoxType();
    List<BoxTypeDto> selectAllBoxTypeByTypeId(BoxTypeDto boxTypeDto);


    //    상영테이블용
    List<FilmDto> selectFilmIsPlaying();
    FilmDto selectFilmDtoByFilmId(FilmDto filmDto);
    void insertFIlmPlayingTable(FilmPlayingTableDto filmPlayingTableDto);



    ///

}


