package com.project.project6.controller;

import com.project.project6.request.BoardRequest;
import com.project.project6.exception.DuplicateBoardException;
import com.project.project6.service.BoardRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/board")
public class BoardRegisterController {

    private BoardRegisterService boardRegisterService;

    @Autowired
    public void setMemberRegisterService(BoardRegisterService boardRegisterService) {
        this.boardRegisterService = boardRegisterService;
    }

    @RequestMapping("/step1")
    public String registerBoardStep1() {
        return "board/step1";
    }

    @GetMapping("/step2")
    public String registerBoardStep2() {
        return "redirect:/board/step1";
    }

    @PostMapping("/step2")
    public String registerBoardStep2(BoardRequest req, @RequestParam("memberEmail") String email, @RequestPart MultipartFile file, @RequestPart MultipartFile[] files) {
        try {
            System.out.println("length is" + req.getContent().length());
            boardRegisterService.register(req, email, file, files);
            return "board/step2";
        } catch (DuplicateBoardException ex) {
            return "board/step1";
        }
    }

}
