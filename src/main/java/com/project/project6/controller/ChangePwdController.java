package com.project.project6.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.project6.login.AuthInfo;
import com.project.project6.service.ChangePasswordService;
import com.project.project6.exception.WrongIdPasswordException;
import com.project.project6.command.ChangePwdCommand;
import com.project.project6.validator.ChangePwdCommandValidator;

@Controller
@RequestMapping("/user/changePassword")
public class ChangePwdController {

    private ChangePasswordService changePasswordService;

    @Autowired
    public void setChangePasswordService(
            ChangePasswordService changePasswordService) {
        this.changePasswordService = changePasswordService;
    }

    @GetMapping
    public String form(
            @ModelAttribute("command") ChangePwdCommand pwdCmd) {
        return "member/changePwdForm";
    }

    @PostMapping
    public String submit(
            @ModelAttribute("command") ChangePwdCommand pwdCmd,
            Errors errors,
            HttpSession session) {
        new ChangePwdCommandValidator().validate(pwdCmd, errors);
        if (errors.hasErrors()) {
            return "member/changePwdForm";
        }
        AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo");
        try {
            changePasswordService.changePassword(
                    authInfo.getEmail(),
                    pwdCmd.getCurrentPassword(),
                    pwdCmd.getNewPassword());
            return "member/changePwdSuccess";
        } catch (WrongIdPasswordException e) {
            errors.rejectValue("currentPassword", "notMatching");
            return "member/changePwdForm";
        }
    }
}