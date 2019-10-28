package com.project.project6.controller;

import com.project.project6.command.DeleteAccountCommand;
import com.project.project6.exception.WrongPasswordException;
import com.project.project6.login.AuthInfo;
import com.project.project6.member.MemberDTO;
import com.project.project6.service.BoardDeleteService;
import com.project.project6.service.MemberDeleteService;
import com.project.project6.service.MessageService;
import com.project.project6.service.UserProfileService;
import com.project.project6.validator.DeleteAccountCommandValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class MemberDeleteController {

    private MemberDeleteService memberDeleteService;
    private UserProfileService userProfileService;
    private BoardDeleteService boardDeleteService;
    private MessageService messageService;

    @Autowired
    public void setMemberDeleteService(MemberDeleteService memberDeleteService) {
        this.memberDeleteService = memberDeleteService;
    }

    @Autowired
    public void setUserProfileService(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @Autowired
    public void setBoardDeleteService(BoardDeleteService boardDeleteService) {
        this.boardDeleteService = boardDeleteService;
    }

    @Autowired
    public void setMessageService(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/user/delete")
    public String deleteAccount(@ModelAttribute("command") DeleteAccountCommand deleteCommand) {
        return "member/delete";
    }

    @PostMapping("/user/delete")
    public String deleteAccountAccess(@ModelAttribute("command") DeleteAccountCommand deleteCommand,
                                      Errors errors,
                                      HttpSession session) {
        new DeleteAccountCommandValidator().validate(deleteCommand, errors);
        if (errors.hasErrors()) {
            return "member/delete";
        }
        AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo");
        try {
            MemberDTO member = userProfileService.getUserProfile(authInfo.getId());
            boolean isMatch = member.matchPassword(deleteCommand.getPassword());
            if (!isMatch) {
                throw new WrongPasswordException();
            }
            messageService.deleteMessageByMemId(authInfo.getId());
            boardDeleteService.deleteBoardByMemId(authInfo.getId());
            memberDeleteService.deleteMemberByMemId(authInfo.getId());
            session.invalidate();
            return "redirect:/";
        } catch (WrongPasswordException e) {
            errors.rejectValue("password", "notMatching");
            return "member/delete";
        }
    }

}
