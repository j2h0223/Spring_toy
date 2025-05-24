package com.toy.toycinema.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class RegisterFilmAdditionalInfoDto {
    private int filmId;
    private String filmName;
    private String filmOriginalName;
    private String urlMain;
    private int countUrlYoutube;//
    private List<String> urlYoutubeList = new ArrayList<>();
    private int countDirector;//
    private List<PeopleDto> directorDtoList = new ArrayList<>();
    private int countActor;//
    private List<PeopleDto> actorDtoList = new ArrayList<>();
    private int countGenre;//
    private List<GenreDto> genreDtoList = new ArrayList<>();
    private int countType;//
    private List<TypeDto> typeDtoList = new ArrayList<>();
    private List<FilmPosterDto> filmPosterDtoList = new ArrayList<>();
}
