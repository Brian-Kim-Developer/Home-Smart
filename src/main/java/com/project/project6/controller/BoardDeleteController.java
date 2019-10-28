package com.project.project6.controller;

import com.project.project6.service.BoardDeleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
public class BoardDeleteController {

    private BoardDeleteService boardDeleteService;

    @Autowired
    public void setBoardDeleteService(BoardDeleteService boardDeleteService) {
        this.boardDeleteService = boardDeleteService;
    }

    @RequestMapping("/delete/{bno}")
    public String deleteBoardOnBNo(@PathVariable("bno") Long bno) {
        boardDeleteService.deleteBoardByBNo(bno);
        return "redirect:/boards";
    }

}
