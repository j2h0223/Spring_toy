package com.toy.toycinema.operator.service;

import com.toy.toycinema.dto.FilmDto;

import java.util.*;

public interface RestOperatorFilmService {
    List<Map<String, Object>> findFilmTotalList();

    Map<String, Object> findFilmDetailInfo(FilmDto filmDto);

    Map<String, Object> findAudienceCountByGender(FilmDto filmDto);
    List<Map<String, Object>>  findAudienceCountByDate(FilmDto filmDto);
}
