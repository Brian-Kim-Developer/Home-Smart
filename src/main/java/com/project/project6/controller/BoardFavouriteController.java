package com.project.project6.controller;

import com.project.project6.board.BoardVO;
import com.project.project6.service.BoardFavouriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardFavouriteController {

    private BoardFavouriteService boardFavouriteService;

    @Autowired
    public void setBoardFavouriteService(BoardFavouriteService boardFavouriteService) {
        this.boardFavouriteService = boardFavouriteService;
    }

    @RequestMapping("/favourite")
    public String BoardFavourite(@RequestParam("memberId") Long memId, Model model) {
        List<BoardVO> boards = boardFavouriteService.getFavouriteBoards(memId);
        model.addAttribute("boards", boards);
        return "board/favourite";
    }

    @PostMapping("/add/favourite")
    public String addFavourite(@RequestParam("memberId") Long memId, @RequestParam("bno") Long bno) {
        if (!boardFavouriteService.getFavouriteBoard(memId, bno)) {
            boardFavouriteService.addFavouriteBoard(memId, bno);
        }
        return "redirect:/board/favourite?memberId=" + memId;
    }

    @RequestMapping("/delete/favourite")
    public String deleteFavourite(@RequestParam("memberId") Long memId, @RequestParam("bno") Long bno) {
        boardFavouriteService.deleteFavouriteBoard(memId, bno);
        return "redirect:/board/favourite?memberId=" + memId;
    }

}
