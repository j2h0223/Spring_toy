package com.toy.toycinema.operator.controller;

import com.toy.toycinema.dto.*;

import com.toy.toycinema.operator.service.OperatorFilmRegisterService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

//@Controller
//@RequestMapping("operator")
public class OperatorFilmRegisterControllerOld {

    private final OperatorFilmRegisterService operatorFilmRegisterService;

    public OperatorFilmRegisterControllerOld(OperatorFilmRegisterService operatorFilmRegisterService) {
        this.operatorFilmRegisterService = operatorFilmRegisterService;
    }

    @RequestMapping("homePage")
    public String operatorHomePage() {

        return "/operator/homePage";
    }

    @RequestMapping("mainPage")
    public String operatorMainPage() {

        return "/operator/mainPage";
    }

    @RequestMapping("registerFilmPage")
    public String operatorRegisterFilmPage() {

        return "/operator/registerFilmPage";
    }

    @RequestMapping("registerFilmDetailPage")
    public String operatorRegisterFilmDetailPage() {

        return "/operator/registerFilmDetailPage";
    }

    @RequestMapping("registerFilmAdditionalInfoPage")
    public String operatorRegisterFilmAdditionalInfoPage() {

        return "/operator/registerFilmAdditionalInfoPage";
    }

    @RequestMapping("registerFilmNameProcess")
    public String registerFilmNameProcess(
            @ModelAttribute RegisterFilmBasicInfoDto registerFilmBasicInfoDto,
            Model model
//            HttpSession session
    ) {
        System.out.println("registerFilmDto = " + registerFilmBasicInfoDto);

//        operatorFilmRegisterService.registerFilmBasicInfo(registerFilmDto);
        System.out.println("registerFilmDto = " + registerFilmBasicInfoDto);

//        countLoop(registerFilmDto);

        model.addAttribute("registerFilmDto", registerFilmBasicInfoDto);

        return "/operator/registerFilmDetailPage";
    }

//    private static void countLoop(RegisterFilmDto registerFilmDto) {
//        for (int i = 0; i < registerFilmDto.getCountGenre(); i++) {
//            registerFilmDto.getGenreDtoList().add(new GenreDto());
//        }
//
//        for (int i = 0; i < registerFilmDto.getCountDirector(); i++) {
//            registerFilmDto.getDirectorDtoList().add(new PeopleDto());
//        }
//
//        for (int i = 0; i < registerFilmDto.getCountActor(); i++) {
//            registerFilmDto.getActorDtoList().add(new PeopleDto());
//        }
//
//        for (int i = 0; i < registerFilmDto.getCountUrlYoutube(); i++) {
//            registerFilmDto.getUrlYoutubeList().add("");
//        }
//    }

    @RequestMapping("registerFilmDetailProcess")
    public String registerFilmDetailProcess(
            @ModelAttribute RegisterFilmBasicInfoDto registerFilmBasicInfoDto,
            Model model
    ) {
        System.out.println("registerFilmDto = " + registerFilmBasicInfoDto);

//        operatorFilmService.registerFilmBasicInfo(registerFilmDto);
//        Map<String, Object> peopleRegistrationRequiredMap = operatorFilmRegisterService.registerFilmDetailInfo(registerFilmDto);
        System.out.println("registerFilmDto = " + registerFilmBasicInfoDto);

        model.addAttribute("registerFilmDto", registerFilmBasicInfoDto);
//        model.addAttribute("peopleRegistrationRequiredMap", peopleRegistrationRequiredMap);

        return "/operator/registerFilmAdditionalInfoPage";
    }

//    @RequestMapping("registerFilmAdditionalInfoProcess")
//    public String registerFilmAdditionalInfoProcess(
//
//    ) {
//
//
//    }

}
