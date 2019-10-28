package com.project.project6.controller;

import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.project.project6.board.BoardVO;
import com.project.project6.board.BoardDAO;
import com.project.project6.exception.BoardNotFoundException;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BoardDetailController {

    private BoardDAO boardDAO;

    @Autowired
    public void setBoardDAO(BoardDAO boardDAO) {
        this.boardDAO = boardDAO;
    }

    @GetMapping("/board/{bno}")
    public String detail(@PathVariable("bno") Long bno, Model model) {
        BoardVO board = boardDAO.getBoardByBNo(bno);
        if(board == null) {
            throw new BoardNotFoundException();
        }
        model.addAttribute("board", board);
        return "board/detail";
    }

    @ExceptionHandler(TypeMismatchException.class)
    public String handleTypeMismatchException() {
        return "board/detail";
    }

    @ExceptionHandler(BoardNotFoundException.class)
    public String handleNotFoundException() {
        return "board/detail";
    }
}
