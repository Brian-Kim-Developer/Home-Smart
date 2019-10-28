package com.project.project6.controller;

import com.project.project6.board.BoardVO;
import com.project.project6.service.BoardMyListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardMyListController {

    private BoardMyListService boardMyListService;

    @Autowired
    public void setBoardMyListService(BoardMyListService boardMyListService) {
        this.boardMyListService = boardMyListService;
    }

    @RequestMapping("/myList")
    public String BoardMyList(@RequestParam("memberId") Long memId, Model model) {
        List<BoardVO> boards = boardMyListService.getMyList(memId);
        model.addAttribute("boards", boards);
        return "board/myList";
    }

}
