package com.toy.toycinema.main.mapper;

import com.toy.toycinema.dto.*;

import org.apache.ibatis.annotations.Mapper;

import java.util.*;

@Mapper
public interface RestReservationFilmMapper {

    // 000
    List<DateDto> selectAllDataDtoList(SelectFilmReservationDto selectFilmReservationDto);
    List<FilmDto> selectAllFilmDtoList(SelectFilmReservationDto selectFilmReservationDto);
    List<TheaterDto> selectAllTheaterDtoList(SelectFilmReservationDto selectFilmReservationDto);


    // 001
        // List<DateDto> - 모든 날짜

    List<FilmDto> selectFilmDtoListByDate(SelectFilmReservationDto selectFilmReservationDto);
    List<FilmDto> selectFilmDtoWhiteListByDate(SelectFilmReservationDto selectFilmReservationDto);

    List<TheaterDto> selectTheaterDtoListByDate(SelectFilmReservationDto selectFilmReservationDto);
    List<TheaterDto> selectTheaterDtoWhiteListByDate(SelectFilmReservationDto selectFilmReservationDto);


    // 010
        // List<TheaterDto> - 모든 영화관
    List<DateDto> selectDateDtoListByTheater(SelectFilmReservationDto selectFilmReservationDto);
        //    List<DateDto> selectDateDtoListWhiteTheater(SelectFilmReservationDto selectFilmReservationDto);

    List<FilmDto> selectFilmDtoListByTheater(SelectFilmReservationDto selectFilmReservationDto);
    List<FilmDto> selectFilmDtoWhiteListByTheater(SelectFilmReservationDto selectFilmReservationDto);

    // 100
        // List<FilmDto> - 모든 영화
    List<DateDto> selectDateDtoListByFilm(SelectFilmReservationDto selectFilmReservationDto);

    List<TheaterDto> selectTheaterDtoListByFilm(SelectFilmReservationDto selectFilmReservationDto);
    List<TheaterDto> selectTheaterDtoWhiteListByFilm(SelectFilmReservationDto selectFilmReservationDto);

    // 011
        // 극장 날짜
    List<FilmDto> selectFilmDtoListByTheaterAndDate(SelectFilmReservationDto selectFilmReservationDto);
    List<FilmDto> selectFilmDtoWhiteListByTheaterAndDate(SelectFilmReservationDto selectFilmReservationDto);

    // 101
        // 영화 날짜
    List<TheaterDto> selectTheaterDtoListByFilmAndDate(SelectFilmReservationDto selectFilmReservationDto);
    List<TheaterDto> selectTheaterDtoWhiteListByFilmAndDate(SelectFilmReservationDto selectFilmReservationDto);

    // 110
    // 영화 극장
    List<DateDto> selectDateDtoListByFilmAndTheater(SelectFilmReservationDto selectFilmReservationDto);

    // 111
    List<BoxTypeDto> selectBoxTypeDtoListForReservation(SelectFilmReservationDto selectFilmReservationDto);

    TypeDto selectTypeDtoForReservation(BoxTypeDto boxTypeDto);
    BoxDto selectBoxDtoForReservation(BoxTypeDto boxTypeDto);
    List<TimeDto> selectTimeDtoListForReservation(SelectFilmReservationDto selectFilmReservationDto);
    Integer countFilmReservationSeatForReservation(SelectFilmReservationDto selectFilmReservationDto);
    FilmPlayingTableDto selectFilmPlayingTableDtoForReservation(SelectFilmReservationDto selectFilmReservationDto);

    // 영화 정보
    FilmDto selectFilmDtoByFilm(FilmDto filmDto);
    FilmPosterDto selectFilmPosterDtoByFilm(FilmDto filmDto);

    // 극장 정보
    TheaterDto selectTheaterDtoByTheater(TheaterDto theaterDto);

    // 상영관 정보
    BoxDto selectBoxDtoByFilmPlayingTable(FilmPlayingTableDto filmPlayingTableDto);
    TypeDto selectTypeDtoByFilmPlayingTable(FilmPlayingTableDto filmPlayingTableDto);
    FilmPlayingTableDto selectFilmPlayingTableDtoByFilmPlayingTable(FilmPlayingTableDto filmPlayingTableDto);

    TheaterDto selectTheaterDtoByFilmPlayingTable(FilmPlayingTableDto filmPlayingTableDto);
    Integer countFilmReservationSeatByFilmPlayingTable(FilmPlayingTableDto filmPlayingTableDto);
    FilmDto selectFilmDtoByFilmPlayingTable(FilmPlayingTableDto filmPlayingTableDto);

}