package com.toy.toycinema.operator.controller;


import com.toy.toycinema.dto.*;

import com.toy.toycinema.operator.service.OperatorFilmRegisterService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("operator/registerFilm")
public class OperatorFilmRegisterController {


    private final OperatorFilmRegisterService operatorFilmRegisterService;

    public OperatorFilmRegisterController(OperatorFilmRegisterService operatorFilmRegisterService) {
        this.operatorFilmRegisterService = operatorFilmRegisterService;
    }

    @RequestMapping("registerFilmBasicInfoPage")
    public String operatorRegisterFilmPage() {
        return "/operator/registerFilm/registerFilmBasicInfoPage";
    }

    @RequestMapping("registerFilmBasicInfoProcess")
    public String registerFilmBasicInfoProcess(
            @ModelAttribute RegisterFilmBasicInfoDto registerFilmBasicInfoDto,
            Model model
    ) {
//        System.out.println("registerFilmDto = " + registerFilmBasicInfoDto);
        operatorFilmRegisterService.registerFilmBasicInfo(registerFilmBasicInfoDto);
//        System.out.println("registerFilmDto = " + registerFilmBasicInfoDto);

        model.addAttribute("filmId", registerFilmBasicInfoDto.getId());
        model.addAttribute("filmName", registerFilmBasicInfoDto.getFilmName());
        model.addAttribute("filmOriginalName", registerFilmBasicInfoDto.getOriginalName());

        return "/operator/registerFilm/registerFilmAdditionalInfoPage";
    }

    @RequestMapping("registerFilmAdditionalInfoProcess")
    public String registerFilmAdditionalInfoProcess(
            @ModelAttribute RegisterFilmAdditionalInfoDto registerFilmAdditionalInfoDto,
            HttpSession session,
            Model model
    ) {
        session.setAttribute("registerFilmAdditionalInfoDtoInSession", registerFilmAdditionalInfoDto);
        registerFilmAdditionalInfoDto.setGenreDtoList(new ArrayList<>(Collections.nCopies(registerFilmAdditionalInfoDto.getCountGenre(), new GenreDto())));
//        System.out.println("registerFilmAdditionalInfoDto = " + registerFilmAdditionalInfoDto);

        model.addAttribute("registerFilmAdditionalInfoDto", registerFilmAdditionalInfoDto);

//        return "/operator/registerFilm/registerFilmPosterPage";

        return "/operator/registerFilm/registerFilmGenrePage";
    }


    @RequestMapping("registerFilmGenreProcess")
    public String registerFilmGenreProcess(
            @ModelAttribute RegisterFilmAdditionalInfoDto registerFilmAdditionalInfoDtoParam,
            HttpSession session,
            Model model
    ) {
        RegisterFilmAdditionalInfoDto registerFilmAdditionalInfoDtoInSession = (RegisterFilmAdditionalInfoDto) session.getAttribute("registerFilmAdditionalInfoDtoInSession");

        registerFilmAdditionalInfoDtoParam.setFilmId(registerFilmAdditionalInfoDtoInSession.getFilmId());
        operatorFilmRegisterService.registerFilmGenre(registerFilmAdditionalInfoDtoParam);

        registerFilmAdditionalInfoDtoInSession.setUrlYoutubeList(new ArrayList<>(Collections.nCopies(registerFilmAdditionalInfoDtoInSession.getCountUrlYoutube(), "")));
        model.addAttribute("registerFilmAdditionalInfoDto", registerFilmAdditionalInfoDtoInSession);

        return "/operator/registerFilm/registerFilmYoutubePage";
    }

    @RequestMapping("registerFilmYoutubeProcess")
    public String registerFilmYoutubeProcess(
            @ModelAttribute RegisterFilmAdditionalInfoDto registerFilmAdditionalInfoDtoParam,
            HttpSession session,
            Model model
    ) {
        RegisterFilmAdditionalInfoDto registerFilmAdditionalInfoDtoInSession = (RegisterFilmAdditionalInfoDto) session.getAttribute("registerFilmAdditionalInfoDtoInSession");

        registerFilmAdditionalInfoDtoParam.setFilmId(registerFilmAdditionalInfoDtoInSession.getFilmId());
        operatorFilmRegisterService.registerFilmUrlYoutube(registerFilmAdditionalInfoDtoParam);

        registerFilmAdditionalInfoDtoInSession.setDirectorDtoList(new ArrayList<>(Collections.nCopies(registerFilmAdditionalInfoDtoInSession.getCountDirector(), new PeopleDto())));
        registerFilmAdditionalInfoDtoInSession.setActorDtoList(new ArrayList<>(Collections.nCopies(registerFilmAdditionalInfoDtoInSession.getCountActor(), new PeopleDto())));

        model.addAttribute("registerFilmAdditionalInfoDto", registerFilmAdditionalInfoDtoInSession);

        return "/operator/registerFilm/registerFilmDirectorAndActorPage";
    }

    @RequestMapping("registerFilmDirectorAndActorProcess")
    public String registerFilmDirectorAndActorProcess(
            @ModelAttribute RegisterFilmAdditionalInfoDto registerFilmAdditionalInfoDtoParam,
            HttpSession session,
            Model model
    ) {
        RegisterFilmAdditionalInfoDto registerFilmAdditionalInfoDtoInSession = (RegisterFilmAdditionalInfoDto) session.getAttribute("registerFilmAdditionalInfoDtoInSession");

        registerFilmAdditionalInfoDtoParam.setFilmId(registerFilmAdditionalInfoDtoInSession.getFilmId());
//        for (PeopleDto peopleDto : registerFilmAdditionalInfoDtoParam.getDirectorDtoList()) {
//            System.out.println("peopleDto1 = " + peopleDto);
//        }
//        for (PeopleDto peopleDto : registerFilmAdditionalInfoDtoParam.getActorDtoList()) {
//            System.out.println("peopleDto2 = " + peopleDto);
//        }

        Map<String, List<PeopleDto>> peopleMap = operatorFilmRegisterService.registerFilmDirectorAndActor(registerFilmAdditionalInfoDtoParam);

//        for (PeopleDto peopleDto : peopleMap.get("peopleRationRequiredForDirector")) {
//            System.out.println("peopleDto3 = " + peopleDto);
//        }
//        for (PeopleDto peopleDto : peopleMap.get("peopleRegistrationRequiredForActor")) {
//            System.out.println("peopleDto4 = " + peopleDto);
//        }

        registerFilmAdditionalInfoDtoInSession.setDirectorDtoList(peopleMap.get("peopleRationRequiredForDirector"));
        registerFilmAdditionalInfoDtoInSession.setActorDtoList(peopleMap.get("peopleRegistrationRequiredForActor"));


        model.addAttribute("registerFilmAdditionalInfoDto", registerFilmAdditionalInfoDtoInSession);


        if (registerFilmAdditionalInfoDtoInSession.getDirectorDtoList() == null || registerFilmAdditionalInfoDtoInSession.getActorDtoList() == null) {
            return "/operator/registerFilm/registerFilmTypePage";
        }

        return "/operator/registerFilm/registerFilmPeopleInfoPage";
    }

    @RequestMapping("registerFilmPeopleInfoProcess")
    public String registerFilmPeopleInfoProcess(
            @ModelAttribute RegisterFilmAdditionalInfoDto registerFilmAdditionalInfoDtoParam,
            HttpSession session,
            Model model
    ) {
        System.out.println("registerFilmAdditionalInfoDtoParam");
        RegisterFilmAdditionalInfoDto registerFilmAdditionalInfoDtoInSession = (RegisterFilmAdditionalInfoDto) session.getAttribute("registerFilmAdditionalInfoDtoInSession");
        registerFilmAdditionalInfoDtoParam.setFilmId(registerFilmAdditionalInfoDtoInSession.getFilmId());
        operatorFilmRegisterService.registerFilmPeople(registerFilmAdditionalInfoDtoParam);

        registerFilmAdditionalInfoDtoInSession.setTypeDtoList(new ArrayList<>(Collections.nCopies(registerFilmAdditionalInfoDtoInSession.getCountType(), new TypeDto())));
        model.addAttribute("registerFilmAdditionalInfoDto", registerFilmAdditionalInfoDtoInSession);

        return "/operator/registerFilm/registerFilmTypePage";
    }

    @RequestMapping("registerFilmTypeProcess")
    public String registerFilmTypeProcess(
            @ModelAttribute RegisterFilmAdditionalInfoDto registerFilmAdditionalInfoDtoParam,
            HttpSession session,
            Model model
    ) {
        RegisterFilmAdditionalInfoDto registerFilmAdditionalInfoDtoInSession = (RegisterFilmAdditionalInfoDto) session.getAttribute("registerFilmAdditionalInfoDtoInSession");
        registerFilmAdditionalInfoDtoParam.setFilmId(registerFilmAdditionalInfoDtoInSession.getFilmId());
        operatorFilmRegisterService.registerFilmType(registerFilmAdditionalInfoDtoParam);

        return "/operator/registerFilm/registerFilmPosterPage";
    }

    @RequestMapping("registerFilmPosterProcess")
    public String registerFilmPosterProcess(
            @ModelAttribute RegisterFilmAdditionalInfoDto registerFilmAdditionalInfoDtoParam,
            @RequestParam("uploadFiles") List<MultipartFile> uploadFiles,
            HttpSession session,
            Model model
    ) {
        for (MultipartFile multipartFile : uploadFiles) {
            System.out.println("multipartFile = " + multipartFile);
        }
        RegisterFilmAdditionalInfoDto registerFilmAdditionalInfoDtoInSession = (RegisterFilmAdditionalInfoDto) session.getAttribute("registerFilmAdditionalInfoDtoInSession");
        registerFilmAdditionalInfoDtoParam.setFilmId(registerFilmAdditionalInfoDtoInSession.getFilmId());
        registerFilmAdditionalInfoDtoParam.setFilmOriginalName(registerFilmAdditionalInfoDtoInSession.getFilmOriginalName());
        operatorFilmRegisterService.registerFilmPoster(registerFilmAdditionalInfoDtoParam, uploadFiles);


        return "redirect:/operator/registerFilm/checkFilmRegistrationProcessPage";
    }

    @RequestMapping("checkFilmRegistrationProcessPage")
    public String checkFilmRegistrationProcessPage(
            HttpSession session,
            Model model
    ) {


        return "operator/mainPage";
    }
}
