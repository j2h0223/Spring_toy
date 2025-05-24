package com.toy.toycinema.operator.controller;


import com.toy.toycinema.dto.*;

import com.toy.toycinema.operator.service.RestFilmRegisterService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@RestController
@RequestMapping("api/operator")
public class RestFilmRegisterController {

    private final RestFilmRegisterService restFilmRegisterService;

    public RestFilmRegisterController(RestFilmRegisterService restFilmRegisterService) {
        this.restFilmRegisterService = restFilmRegisterService;
    }

    @PostMapping("registerBasicInfo")
    @ResponseBody
    public FilmDto registerBasicInfo(
            @RequestBody FilmDto filmDto
    ) {
//        System.out.println("filmDto = " + filmDto);

        return new FilmDto();
    }

    @PostMapping("registerFilmType")
    @ResponseBody
    public Map<String, Object> registerFilmType(
            @RequestBody Map<String, Object> map
    ) {

        restFilmRegisterService.setFilmTypeInfo(map);

        return Map.of("result", "registerFilmType success");
    }

    @PostMapping("registerFilmGenre")
    @ResponseBody
    public Map<String, Object> registerFilmGenre(
            @RequestBody Map<String, Object> map
    ) {
//        System.out.println("map.get(\"filmGenreList\") = " + map.get("filmGenreList"));
//        System.out.println("map.get(\"id\") = " + map.get("id"));

        restFilmRegisterService.setFilmGenreInfo(map);

        return Map.of("result", "registerFilmGenre success");
    }


    @PostMapping("registerFilmYoutube")
    @ResponseBody
    public Map<String, Object> registerFilmYoutube(
            @RequestBody Map<String, Object> map
    ) {
//        System.out.println("map.get(\"filmGenreList\") = " + map.get("filmGenreList"));
//        System.out.println("map.get(\"id\") = " + map.get("id"));

        restFilmRegisterService.setFilmYoutubeInfo(map);

        return Map.of("result", "registerFilmYoutube success");
    }

    @PostMapping("registerFilmPoster")
    @ResponseBody
    public Map<String, Object> registerFilmPoster(
            @RequestParam("files") List<MultipartFile> files,
            @RequestParam("id") int id
    ) {
        System.out.println("files = " + files);

        for (MultipartFile multipartFile : files) {
            System.out.println("multipartFile = " + multipartFile);

        }

        System.out.println("id = " + id);

//        restFilmRegisterService.setFilmYoutubeInfo(map);

        return Map.of("result", "registerFilmPoster success");
    }
}
