package com.toy.toycinema.main.service;

import com.toy.toycinema.dto.*;

import com.toy.toycinema.main.mapper.MainPageSqlMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MainPageServiceImpl implements MainPageService{

    private final MainPageSqlMapper mainPageSqlMapper;

    public MainPageServiceImpl(MainPageSqlMapper mainPageSqlMapper) {
        this.mainPageSqlMapper = mainPageSqlMapper;
    }

    @Override
    public List<Map<String, Object>> getNowPlayingFilmDtoList() {

        List<Map<String, Object>> list = new ArrayList<>();

        List<FilmDto> filmDtoList = mainPageSqlMapper.selectFilmByIsNowPlaying();
        for (FilmDto filmDto : filmDtoList) {
            FilmPosterDto filmPosterDto = mainPageSqlMapper.selectFilmPosterDtoByFilmId(filmDto);

            Map<String, Object> map = new HashMap<>();
            map.put("filmDto", filmDto);
            map.put("filmPosterDto", filmPosterDto);
            list.add(map);
        }

        return list;
    }


}
