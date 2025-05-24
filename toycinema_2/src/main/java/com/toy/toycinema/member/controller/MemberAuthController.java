package com.toy.toycinema.member.controller;

import com.toy.toycinema.dto.*;

import com.toy.toycinema.member.service.MemberAuthService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("member")
public class MemberAuthController {

    private final MemberAuthService memberAuthService;

    public MemberAuthController(MemberAuthService memberAuthService) {
        this.memberAuthService = memberAuthService;
    }

    @GetMapping("registerPage")
    public String registerPage() {
        return "/member/registerPage";
    }

    @PostMapping("registerProcess")
    public String registerProcess(
            @ModelAttribute MemberDto memberDto
    ) {
        memberAuthService.registerMember(memberDto);
        return "/member/registerSuccessPage";
    }

    @PostMapping("registerSuccessPage")
    public String registerSuccessPage() {
        return "/member/registerSuccessPage";
    }

    @GetMapping("loginPage")
    public String loginPage() {
        return "/member/loginPage";
    }

    @PostMapping("loginProcess")
    public String loginProcess(
            @RequestParam("accountName") String accountName,
            @RequestParam("password") String password,
            HttpSession session
    ) {
        MemberDto memberDto = memberAuthService.authenticationMember(accountName, password);
        if (memberDto == null) {
            return "redirect:/member/loginPage";
        } else {
            session.setAttribute("memberLoginInfo", memberDto);
            return "redirect:/main/homePage";
        }
    }

    @GetMapping("logoutProcess")
    public String logoutProcess(HttpSession session) {
        session.invalidate();
        return "redirect:/main/homePage";
    }

    @RequestMapping("myPage")
    public String myPage(
            HttpSession session,
            Model model
    ) {
        MemberDto memberDto = (MemberDto) session.getAttribute("memberLoginInfo");
        if (memberDto == null) {
            return "redirect:/member/loginPage";
        } else {
            model.addAttribute("memberDto", memberDto);
            return "/member/restMyPage";
        }
    }

}
