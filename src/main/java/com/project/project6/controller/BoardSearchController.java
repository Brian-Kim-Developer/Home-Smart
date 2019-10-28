package com.project.project6.controller;

import com.project.project6.request.BoardSearchRequest;
import com.project.project6.board.BoardVO;
import com.project.project6.service.BoardSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class BoardSearchController {

    private BoardSearchService boardSearchService;

    @Autowired
    public void setBoardSearchService(BoardSearchService boardSearchService) {
        this.boardSearchService = boardSearchService;
    }

    @PostMapping("/board/search")
    public String base(BoardSearchRequest req, Model model) {
        List<BoardVO> boards = boardSearchService.search(req);
        model.addAttribute("boards", boards);

        return "board/list";
    }

}
