package com.project.project6.controller;

import com.project.project6.command.DeleteAccountCommand;
import com.project.project6.exception.WrongPasswordException;
import com.project.project6.login.AuthInfo;
import com.project.project6.member.MemberDTO;
import com.project.project6.service.UserProfileService;
import com.project.project6.validator.DeleteAccountCommandValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class UserProfileController {

    private UserProfileService userProfileService;

    @Autowired
    public void setUserProfileService(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @GetMapping("/user/profile/{id}")
    public String userProfile(@PathVariable("id") Long id, Model model) {
        MemberDTO member = userProfileService.getUserProfile(id);
        model.addAttribute("user", member);
        return "member/profile";
    }

}
