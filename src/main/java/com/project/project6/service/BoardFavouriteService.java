package com.project.project6.service;

import com.project.project6.board.BoardDAO;
import com.project.project6.board.BoardVO;
import com.project.project6.favourite.FavouriteDAO;
import com.project.project6.favourite.FavouriteDTO;

import java.util.ArrayList;
import java.util.List;

public class BoardFavouriteService {

    private FavouriteDAO favouriteDAO;
    private BoardDAO boardDAO;

    public void setFavouriteDAO(FavouriteDAO favouriteDAO) {
        this.favouriteDAO = favouriteDAO;
    }
    public void setBoardDAO(BoardDAO boardDAO) { this.boardDAO = boardDAO; }

    public List<BoardVO> getFavouriteBoards(Long memId) {
        FavouriteDTO favourite = favouriteDAO.getBoardsIdByMemberId(memId);
        if (favourite == null || favourite.getBnos().isEmpty()) {
            return null;
        }
        List<BoardVO> boards = new ArrayList<>();
        for(int i = 0; i < favourite.getBnos().size(); i++) {
            BoardVO board = boardDAO.getBoardByBNo(favourite.getBnos().get(i));
            if (board != null) {
                boards.add(board);
            }
        }
        return boards;
    }

    public boolean getFavouriteBoard(Long memId, Long bno) {
        FavouriteDTO favourite = favouriteDAO.getBoardsIdByMemberId(memId);
        if (favourite == null || favourite.getBnos().isEmpty()) {
            return false;
        }
        boolean isExist = false;
        for(int i = 0; i < favourite.getBnos().size(); i++) {
            if(bno == favourite.getBnos().get(i)){
                isExist = true;
            }
        }
        return isExist;
    }

    public void addFavouriteBoard(Long memId, Long bno) {
        favouriteDAO.insertBoardId(memId, bno);
    }

    public void deleteFavouriteBoard(Long memId, Long bno) {
        favouriteDAO.deleteBoardIdByBNo(memId, bno);
    }

}
