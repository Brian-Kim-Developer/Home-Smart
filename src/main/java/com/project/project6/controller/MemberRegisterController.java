package com.project.project6.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;

import com.project.project6.exception.DuplicateMemberException;
import com.project.project6.service.MemberRegisterService;
import com.project.project6.request.RegisterMemberRequest;
import com.project.project6.validator.RegisterRequestValidator;

@Controller
@RequestMapping("/member")
public class MemberRegisterController {

    private MemberRegisterService memberRegisterService;

    @Autowired
    public void setMemberRegisterService(MemberRegisterService memberRegisterService) {
        this.memberRegisterService = memberRegisterService;
    }

    @RequestMapping("/step1")
    public String handleStep1() {
        return "member/step1";
    }

    @GetMapping("/step2")
    public String handleStep2Get() {
        return "redirect:/member/step1";
    }

    @PostMapping("/step2")
    public String handleStep2(@RequestParam(value = "agree", defaultValue = "false") Boolean agree, Model model) {
        if (!agree) {
            return "member/step1";
        }
        model.addAttribute("registerMemberRequest", new RegisterMemberRequest());
        return "member/step2";
    }

    @GetMapping("/step3")
    public String handleStep3Get() {
        return "redirect:/member/step1";
    }

    @PostMapping("/step3")
    public String handleStep3(RegisterMemberRequest regReq, Errors errors) {
        new RegisterRequestValidator().validate(regReq, errors);
        if (errors.hasErrors()) {
            return "member/step2";
        }
        try {
            memberRegisterService.register(regReq);
            return "member/step3";
        } catch (DuplicateMemberException ex) {
            errors.rejectValue("email", "duplicate");
            return "member/step2";
        }
    }

}
