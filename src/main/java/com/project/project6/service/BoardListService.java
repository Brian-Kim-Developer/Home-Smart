package com.project.project6.service;

import com.project.project6.board.BoardDAO;
import com.project.project6.board.BoardVO;

import java.util.List;

public class BoardListService {

    private BoardDAO boardDAO;

    public void setBoardDAO(BoardDAO boardDAO) {
        this.boardDAO = boardDAO;
    }

    public List<BoardVO> getBoardList() {
        List<BoardVO> boards = boardDAO.getBoardList();
        return boards;
    }

}
