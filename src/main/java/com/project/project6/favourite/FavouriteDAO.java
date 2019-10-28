package com.project.project6.favourite;

import com.project.project6.board.BoardVO;

import java.util.List;

public interface FavouriteDAO {

    public FavouriteDTO getBoardsIdByMemberId(Long memId);
    public void insertBoardId(Long memId, Long bno);
    public void deleteBoardIdByBNo(Long memId, Long bno);

}
