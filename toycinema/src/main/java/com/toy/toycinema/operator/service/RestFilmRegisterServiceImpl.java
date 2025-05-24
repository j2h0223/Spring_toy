package com.toy.toycinema.operator.service;

import com.toy.toycinema.dto.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;


@Service
public class RestFilmRegisterServiceImpl implements RestFilmRegisterService {
    @Override
    public void setFilmInfo(FilmDto filmInfo) {

    }

    @Override
    public void setFilmTypeInfo(Map<String, Object> map) {

        ObjectMapper mapper = new ObjectMapper();

        // JSON의 dto 부분을 dto로 파싱
//        MyDto dto = mapper.convertValue(request.get("dto"), MyDto.class);

        List<TypeDto> typeDtoList = mapper.convertValue(
                map.get("filmTypeList"),
                new TypeReference<List<TypeDto>>() {
                });
        int id = (Integer) map.get("id");
    }

    @Override
    public void setFilmGenreInfo(Map<String, Object> map) {
        ObjectMapper mapper = new ObjectMapper();

        List<GenreDto> genreDtoList = mapper.convertValue(
                map.get("filmGenreList"),
                new TypeReference<List<GenreDto>>() {
                });
        int id = (Integer) map.get("id");
    }

    @Override
    public void setFilmYoutubeInfo(Map<String, Object> map) {
        ObjectMapper mapper = new ObjectMapper();

        List<FilmYoutubeDto> filmYoutubeDtoList = mapper.convertValue(
                map.get("filmYoutubeList"),
                new TypeReference<List<FilmYoutubeDto>>() {
                });
        int id = (Integer) map.get("id");
    }

    @Override
    public void registerFilmPoster(MultipartFile files) {
//        loopForEachPoster(uploadFiles, registerFilmAdditionalInfoDto);
//        operatorSqlMapper.updateFilmUrlMain(registerFilmAdditionalInfoDto.getUrlMain(), registerFilmAdditionalInfoDto.getFilmId());
//        for (FilmPosterDto filmPosterDto : registerFilmAdditionalInfoDto.getFilmPosterDtoList()) {
//            operatorSqlMapper.insertFilmPoster(filmPosterDto);
//        }
    }
}
