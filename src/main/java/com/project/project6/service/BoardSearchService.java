package com.project.project6.service;

import com.project.project6.board.BoardDAO;
import com.project.project6.request.BoardSearchRequest;
import com.project.project6.board.BoardVO;

import java.util.List;

public class BoardSearchService {

    private BoardDAO boardDAO;

    public void setBoardDAO(BoardDAO boardDAO) { this.boardDAO = boardDAO; }

    public List<BoardVO> search(BoardSearchRequest req) {

        List<BoardVO> boards = boardDAO.getBoardBySearch(req.getDen(), req.getBathroom(), req.getBedroom(), req.getMin(), req.getMax(), req.getType());

        return boards;

    }

}
