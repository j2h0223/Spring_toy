package com.toy.toycinema.operator.service;

import com.toy.toycinema.dto.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

public interface RestFilmRegisterService {

    void setFilmInfo(FilmDto filmInfo);

    void setFilmTypeInfo(Map<String , Object> map);
    void setFilmGenreInfo(Map<String , Object> map);
    void setFilmYoutubeInfo(Map<String , Object> map);
    void registerFilmPoster(MultipartFile files);
}
