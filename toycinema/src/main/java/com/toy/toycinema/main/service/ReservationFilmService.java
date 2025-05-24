package com.toy.toycinema.main.service;

import com.toy.toycinema.dto.*;

import java.util.Map;

public interface ReservationFilmService {

//    List<FilmDto> getAllFilmIsPlaying();
//    List<TheaterDto> getAllTheater();
//    List<FilmPlayingTableDto> getFilmPlayingTable();

    Map<String, Object> getScreeningSchedule(SelectFilmReservationDto selectFilmReservationDto);

    void setReservation(FilmReservationDto filmReservationDto);

    FilmReservationDto getReservationInfo(FilmReservationDto filmReservationDto);
}
