package com.toy.toycinema.people.controller;


import com.toy.toycinema.dto.*;

import com.toy.toycinema.people.service.PeopleInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("people")
public class PeopleController {


    private final PeopleInfoService peopleInfoService;

    public PeopleController(PeopleInfoService peopleInfoService) {
        this.peopleInfoService = peopleInfoService;
    }

    @RequestMapping("peoplePage/{id}")
    public String peoplePage(
            @PathVariable("id") int peopleId,
            Model model
    ){
        PeopleDto peopleDto = new PeopleDto();
        peopleDto.setId(peopleId);

        Map<String, Object> peopleFilmInfo = peopleInfoService.getPeopleFilm(peopleDto);
        model.addAttribute("peopleFilmInfo", peopleFilmInfo);

        return "/people/peoplePage";
    }
}
