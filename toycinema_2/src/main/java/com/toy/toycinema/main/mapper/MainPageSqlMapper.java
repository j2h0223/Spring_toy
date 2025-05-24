package com.toy.toycinema.main.mapper;

import com.toy.toycinema.dto.*;

import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface MainPageSqlMapper {
    List<FilmDto> selectFilmByIsNowPlaying();

    FilmPosterDto selectFilmPosterDtoByFilmId(FilmDto filmDto);

    // 상영 테이블 - 예매
    List<FilmPlayingTableDto> selectFilmPlayingTableAfterNow(SelectFilmReservationDto selectFilmReservationDto);

    LocalDateTime getCurrentDatabaseTime();

    List<FilmPlayingTableDto> selectFilmPlayingTableListByDate(FilmPlayingTableDto filmPlayingTableDto);
    List<FilmPlayingTableDto> selectFilmPlayingTableListByBoxTypeId(FilmPlayingTableDto filmPlayingTableDto);
    List<FilmPlayingTableDto> selectFilmPlayingTableListByFilmId(FilmPlayingTableDto filmPlayingTableDto);

    List<BoxTypeDto> selectBoxTypeListByBoxTypeId(BoxTypeDto boxTypeDto);
    List<BoxTypeDto> selectBoxTypeListByBoxId(BoxTypeDto boxTypeDto);

    List<FilmDto> selectFilmListByFilmId(FilmDto filmDto);

    List<BoxDto> selectBoxListByBoxId(BoxDto boxDto);
    List<BoxDto> selectBoxListByTheaterId(BoxDto boxDto);

    List<TheaterDto> selectTheaterListByTheaterId(TheaterDto theaterDto);
    List<TheaterDto> selectAllTheater();


    // 0개 선택

    // 날짜
    List<DateDto> selectAllDate(SelectFilmReservationDto selectFilmReservationDto);

    // 영화
    List<FilmDto> selectAllFilm(SelectFilmReservationDto selectFilmReservationDto);
//    List<FilmDto> selectRemainAllFilm(SelectFilmReservationDto selectFilmReservationDto);

    // 극장
    List<TheaterDto> selectAllTheater(SelectFilmReservationDto selectFilmReservationDto);
//    List<TheaterDto> selectRemainAllTheater(SelectFilmReservationDto selectFilmReservationDto);


    // 하나만 선택 ===================

    // 날짜
    List<FilmDto> selectFilmListByPickedDate(SelectFilmReservationDto selectFilmReservationDto);
    List<FilmDto> selectRemainFilmListByPickedDate(SelectFilmReservationDto selectFilmReservationDto);

    List<TheaterDto> selectTheaterListByPickDate(SelectFilmReservationDto selectFilmReservationDto);
    List<TheaterDto> selectRemainTheaterListByPickDate(SelectFilmReservationDto selectFilmReservationDto);

    // 영화
    List<TheaterDto> selectTheaterListByPickedFilm(SelectFilmReservationDto selectFilmReservationDto);
    List<TheaterDto> selectRemainTheaterListByPickedFilm(SelectFilmReservationDto selectFilmReservationDto);

    List<DateDto> selectDateListByPickedFilm(SelectFilmReservationDto selectFilmReservationDto);
    List<DateDto> selectRemainDateListByPickedFilm(SelectFilmReservationDto selectFilmReservationDto);

    // 극장
    List<FilmDto> selectFilmListByPickedTheater(SelectFilmReservationDto selectFilmReservationDto);
    List<FilmDto> selectRemainFilmListByPickedTheater(SelectFilmReservationDto selectFilmReservationDto);

    List<DateDto> selectDateListByPickedTheater(SelectFilmReservationDto selectFilmReservationDto);



    // 두 개 선택 ===========
    List<FilmDto> selectFilmListByPickedTheaterAndFilm(SelectFilmReservationDto selectFilmReservationDto);
    List<TheaterDto> selectTheaterListByPickedTheaterAndFilm(SelectFilmReservationDto selectFilmReservationDto);
    List<DateDto> selectDateListByPickedTheaterAndFilm(SelectFilmReservationDto selectFilmReservationDto);

    List<BoxTypeDto> selectBoxTypeByPickedTheaterAndFilmAndDate(SelectFilmReservationDto selectFilmReservationDto);
    List<BoxDto> selectBoxByPickedTheaterAndFilmAndDate(SelectFilmReservationDto selectFilmReservationDto);

//    Set<DateDto> selectAllDate(SelectFilmReservationDto selectFilmReservationDto);
//    Set<FilmDto> selectAllFilm(SelectFilmReservationDto selectFilmReservationDto);
//    Set<TheaterDto> selectAllTheater(SelectFilmReservationDto selectFilmReservationDto);
//
//    Set<FilmDto> selectFilmListByPickedDate(SelectFilmReservationDto selectFilmReservationDto);
//    Set<TheaterDto> selectTheaterListByPickDate(SelectFilmReservationDto selectFilmReservationDto);
//
//    Set<TheaterDto> selectTheaterListByPickedFilm(SelectFilmReservationDto selectFilmReservationDto);
//    Set<DateDto> selectDateListByPickedFilm(SelectFilmReservationDto selectFilmReservationDto);
//
//    Set<FilmDto> selectFilmListByPickedTheater(SelectFilmReservationDto selectFilmReservationDto);
//    Set<DateDto> selectDateListByPickedTheater(SelectFilmReservationDto selectFilmReservationDto);
//
//    Set<FilmDto> selectFilmListByPickedTheaterAndFilm(SelectFilmReservationDto selectFilmReservationDto);
//    Set<TheaterDto> selectTheaterListByPickedTheaterAndFilm(SelectFilmReservationDto selectFilmReservationDto);
//    Set<DateDto> selectDateListByPickedTheaterAndFilm(SelectFilmReservationDto selectFilmReservationDto);
//
//    Set<BoxTypeDto> selectBoxTypeByPickedTheaterAndFilmAndDate(SelectFilmReservationDto selectFilmReservationDto);
//    Set<BoxDto> selectBoxByPickedTheaterAndFilmAndDate(SelectFilmReservationDto selectFilmReservationDto);


    void insertReservation(FilmReservationDto filmReservationDto);

    FilmReservationDto selectFilmReservationDto(FilmReservationDto filmReservationDto);
}
