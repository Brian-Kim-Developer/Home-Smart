package com.project.project6.service;

import com.project.project6.board.BoardDAO;

public class BoardDeleteService {

    private BoardDAO boardDAO;

    public void setBoardDAO(BoardDAO boardDAO) {
        this.boardDAO = boardDAO;
    }

    public void deleteBoardByBNo(Long bno) {
            boardDAO.deleteByBNo(bno);
    }

    public void deleteBoardByMemId(Long memId) { boardDAO.deleteByMemId(memId); }

}
