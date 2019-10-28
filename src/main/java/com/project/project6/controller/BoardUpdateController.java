package com.project.project6.controller;

import com.project.project6.request.BoardRequest;
import com.project.project6.exception.BoardNotFoundException;
import com.project.project6.service.BoardUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.project.project6.board.BoardDAO;
import com.project.project6.board.BoardVO;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/board")
public class BoardUpdateController {

    private BoardUpdateService boardUpdateService;

    @Autowired
    public void setBoardUpdateService(BoardUpdateService boardUpdateService) {
        this.boardUpdateService = boardUpdateService;
    }

    @RequestMapping("/update/{bno}")
    public String updateBoardOnBNo(@PathVariable("bno") Long bno, Model model) {

        BoardVO board = boardUpdateService.getBoardByBNo(bno);
        if(board == null) {
            throw new BoardNotFoundException();
        }
        model.addAttribute("board", board);
        return "board/update";
    }

    @PostMapping("/update/service")
    private String updateBoard(BoardRequest req, @RequestParam("bno") Long bno, @RequestParam("memberEmail") String email, @RequestPart MultipartFile file, @RequestPart MultipartFile[] files) throws Exception{
        boardUpdateService.update(req, bno, email, file, files);
        return "redirect:/boards";
    }

}
