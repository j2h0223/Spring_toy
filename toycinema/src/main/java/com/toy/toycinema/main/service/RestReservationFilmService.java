package com.toy.toycinema.main.service;

import com.toy.toycinema.dto.*;

import java.util.*;

public interface RestReservationFilmService {

    Map<String, Object> getFilmTheaterDateBoxTypeTime(SelectFilmReservationDto selectFilmReservationDto);

    Map<String, Object> getSelectedFilmInfo(FilmDto filmDto);
    Map<String, Object> getSelectedTheaterInfo(TheaterDto theaterDto);

    List<Map<String, Object>> getPlayingTableInfo(SelectFilmReservationDto selectFilmReservationDto);

    Map<String, Object> getBoxAndTypeInfo(FilmPlayingTableDto filmPlayingTableDto);

    Map<String, Object> getPlayingTableInfoCanvas(FilmPlayingTableDto filmPlayingTableDto);

    FilmPlayingTableDto getFilmPlayingTableDto(FilmPlayingTableDto filmPlayingTableDto);
}
