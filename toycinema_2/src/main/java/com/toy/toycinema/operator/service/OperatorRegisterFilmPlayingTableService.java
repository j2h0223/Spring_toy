package com.toy.toycinema.operator.service;

import com.toy.toycinema.dto.*;


import java.util.List;
import java.util.Map;

public interface OperatorRegisterFilmPlayingTableService {

    List<Map<String, Object>> getFilmAndTypeInfo();

//    List<Map<String, Object>> getTheaterAndBoxAndTypeInfo(FilmPlayingTableDto filmPlayingTableDto);
    List<Map<String, Object>> getTheaterAndBoxAndTypeInfo(FilmPlayingTableDto filmPlayingTableDto);

    FilmDto getFilmDtoByFilmId(FilmDto filmDto);
    TypeDto getTypeDtoByTypeId(TypeDto typeDto);
    TheaterDto getTheaterDtoByTheaterId(TheaterDto theaterDto);
    BoxDto getBoxDtoByBoxId(BoxDto boxDto);
    BoxTypeDto getBoxTypeDtoByBoxTypeDto(BoxTypeDto boxTypeDto);

    void registerFIlmPlayingTable(FilmPlayingTableDto filmPlayingTableDto);

}
