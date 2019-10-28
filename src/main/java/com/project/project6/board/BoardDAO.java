package com.project.project6.board;

import java.util.List;

public interface BoardDAO {

    // select * from board;
    public List<BoardVO> getBoardList();

    // select * from board where (den = #{den}, bathroom = #{bathroom}, bedroom = #{bedroom}, price = #{price}, type = #{type})
    public List<BoardVO> getBoardBySearch(int den, int bathroom, int bedroom, int min, int max, String type);

    // insert into board (bno, title, content, writer)
    // values (seq_board.nextval, #{title}, #{content}, #{writer})
    public void insert(BoardVO boardVO);

    // select * from board where bno = #{bno}
    public BoardVO getBoardByBNo(Long bno);

    // select * from board where member_id = #{member_id}
    public List<BoardVO> getBoardsByMemberId(Long memId);

    // update board
    // set title = #{title},
    // content = #{content},
    // writer = #{writer}
    // updateDate = sysdate
    // where bno = #{bno}
    public void update(BoardVO boardVO, Long bno);

    // delete board where bno = #{bno}
    public void deleteByBNo(Long bno);

    // delete board where member_id = #{member_id}
    public void deleteByMemId(Long memId);
}
