package com.toy.board.member.controller;

import com.toy.board.dto.ControllerMemberDto;
import com.toy.board.dto.ServiceMemberDto;
import com.toy.board.member.service.MemberAuthService;
import com.toy.board.member.service.MemberRegisterService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("member")
public class MemberController {

    private final MemberRegisterService memberRegisterService;
    private final MemberAuthService memberAuthService;

    @Autowired
    public MemberController(MemberRegisterService memberRegisterService, MemberAuthService memberAuthService) {
        this.memberRegisterService = memberRegisterService;
        this.memberAuthService = memberAuthService;
    }

    @RequestMapping("loginPage")
    public String showLoginPage() {
        return "member/loginPage";
    }

    @RequestMapping("joinPage")
    public String showJoinPage() {
        return "member/joinPage";
    }

    @RequestMapping("registerProcess")
    public String registerProcess(
            @ModelAttribute ControllerMemberDto controllerMemberDto
    ){
        memberRegisterService.registerMember(controllerMemberDto);
        return "/member/registerResultPage";
    }

    @RequestMapping("loginProcess")
    public String loginProcess(
            @RequestParam("accountName") String accountName,
            @RequestParam("password") String password,
            HttpSession httpSession
    ){
        try {
            ServiceMemberDto serviceMemberDto = memberAuthService.login(accountName, password);

            httpSession.setAttribute("LOGIN_MEMBER", serviceMemberDto);

            return "redirect:/";
        } catch (IllegalArgumentException e) {
            return "member/loginPage";
        }
    }

    @RequestMapping("logoutProcess")
    public String logoutProcess(HttpSession httpSession){
        httpSession.invalidate();

        return "redirect:/";
    }
}
