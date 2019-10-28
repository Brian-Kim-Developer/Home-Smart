package com.project.project6.controller;

import com.project.project6.board.BoardDAO;
import com.project.project6.board.BoardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class MainController {

    private BoardDAO boardDAO;

    @Autowired
    public void setBoardDAO(BoardDAO boardDAO) {
        this.boardDAO = boardDAO;
    }

    @RequestMapping("/")
    public String main(Model model) {
        List<BoardVO> boards = boardDAO.getBoardList();
        model.addAttribute("boards", boards);
        return "main";
    }
}
