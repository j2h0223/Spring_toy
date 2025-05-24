package com.toy.toycinema.operator.service;

import com.toy.toycinema.dto.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface OperatorFilmRegisterService {
    void registerFilmBasicInfo(RegisterFilmBasicInfoDto registerFilmBasicInfoDto);
    void registerFilmGenre(RegisterFilmAdditionalInfoDto registerFilmAdditionalInfoDto);
    void registerFilmUrlYoutube(RegisterFilmAdditionalInfoDto registerFilmAdditionalInfoDto);
    Map<String, List<PeopleDto>> registerFilmDirectorAndActor(RegisterFilmAdditionalInfoDto registerFilmAdditionalInfoDto);

    void registerFilmPeople(RegisterFilmAdditionalInfoDto registerFilmAdditionalInfoDto);
    void registerFilmType(RegisterFilmAdditionalInfoDto registerFilmAdditionalInfoDto);

//    FilmPosterDto makePathForPoster(byte[] buffer, String originalFilename, RegisterFilmAdditionalInfoDto registerFilmAdditionalInfoDto);

    void registerFilmPoster(RegisterFilmAdditionalInfoDto registerFilmAdditionalInfoDto, List<MultipartFile> uploadFiles);
}
