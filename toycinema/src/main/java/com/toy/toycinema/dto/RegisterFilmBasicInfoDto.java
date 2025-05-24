package com.toy.toycinema.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class RegisterFilmBasicInfoDto {
    private int id;//
    private String filmName;//
    private String originalName;//
    private String story;//
    private String country;//
    private LocalDate openingDay;//
    private LocalDate endDay;
    private int runningTime;//
    private int filmRating;//
    private boolean isNowPlaying;
    private LocalDateTime createdAt;

//    private String urlMain;//
//    //    private List<String> urlSub = new ArrayList<>();
////    private List<MultipartFile> uploadFiles = new ArrayList<>();
//    private List<MultipartFile> uploadFiles;//
//
//    private int countUrlYoutube;//
//    private List<String> urlYoutubeList = new ArrayList<>();
//    private int countDirector;//
//    private List<PeopleDto> directorDtoList = new ArrayList<>();
//    private int countActor;//
//    private List<PeopleDto> actorDtoList = new ArrayList<>();
//    private int countGenre;//
//    private List<GenreDto> genreDtoList = new ArrayList<>();
}
