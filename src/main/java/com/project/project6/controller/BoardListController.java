
package com.project.project6.controller;

import com.project.project6.board.BoardVO;
import com.project.project6.file.FileVO;
import com.project.project6.service.BoardListService;
import com.project.project6.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

@Controller
public class BoardListController {

    private BoardListService boardListService;
    private FileService fileService;

    @Autowired
    public void setBoardListService(BoardListService boardListService) {
        this.boardListService = boardListService;
    }

    @Autowired
    public void setFileService(FileService fileService) { this.fileService = fileService; }

    @RequestMapping("/boards")
    public String list(Model model) {
        List<BoardVO> boards = boardListService.getBoardList();
        model.addAttribute("boards", boards);
        return "board/list";
    }

}
