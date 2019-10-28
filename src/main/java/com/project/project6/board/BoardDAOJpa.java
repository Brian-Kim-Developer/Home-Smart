package com.project.project6.board;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.project.project6.file.FileVO;
import com.project.project6.service.FileService;
import lombok.Synchronized;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.project.project6.exception.BoardNotFoundException;
import org.springframework.transaction.annotation.Transactional;

public class BoardDAOJpa implements BoardDAO {

    @PersistenceContext
    private EntityManager em;
    private BoardRepository boardRepository;
    private FileService fileService;

    @Autowired
    public void setBoardRepository(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Autowired
    public void setFileService(FileService fileService) {
        this.fileService = fileService;
    }

    // select * from board where bno > 0
    @Override
    @Synchronized
    @Transactional
    public List<BoardVO> getBoardList() {
        try {
            TypedQuery<BoardVO> query = em.createQuery("SELECT b FROM BoardVO b WHERE b.bno > 0", BoardVO.class);
            List<BoardVO> result = query.getResultList();
            List<BoardVO> copy = new ArrayList<>(result.size());
            for( int i = 0 ; i < result.size() ; i++ )
            {
                copy.add( result.get( i ));
            }
            Collections.reverse(copy);
            return copy;
        } catch (Exception ex) {
            em.getTransaction().rollback();
            throw ex;
        } finally {
            em.close();
        }
    }

    // select * from board where bno = #{bno}
    @Override
    @Synchronized
    @Transactional
    public BoardVO getBoardByBNo(Long bno) {
        try {
            BoardVO board = em.find(BoardVO.class, bno);
            return board;
        } finally {
            em.close();
        }
    }

    @Override
    @Synchronized
    @Transactional
    public List<BoardVO> getBoardsByMemberId(Long memId) {
        try {
            TypedQuery<BoardVO> query = em.createQuery("SELECT b FROM BoardVO b WHERE (" +
                    "b.memberDTO.id = :memId)", BoardVO.class)
                    .setParameter("memId", memId);

            List<BoardVO> result = query.getResultList();
            List<BoardVO> copy = new ArrayList<>(result.size());
            for( int i = 0 ; i < result.size() ; i++ )
            {
                copy.add( result.get( i ));
            }
            Collections.reverse(copy);
            return copy;
        } catch (Exception ex) {
            em.getTransaction().rollback();
            throw ex;
        } finally {
            em.close();
        }
    }

    @Override
    @Synchronized
    @Transactional
    public List<BoardVO> getBoardBySearch(int den, int bathroom, int bedroom, int min, int max, String type) {
        try {
            TypedQuery<BoardVO> query = em.createQuery("SELECT b FROM BoardVO b WHERE (" +
                    "b.den = :den AND " +
                    "b.bathroom >= :bathroom AND " +
                    "b.bedroom >= :bedroom AND " +
                    "(b.price >= :min AND b.price <= :max) AND " +
                    "b.type = :type)", BoardVO.class)
                    .setParameter("den", den)
                    .setParameter("bathroom", bathroom)
                    .setParameter("bedroom", bedroom)
                    .setParameter("min", min)
                    .setParameter("max", max)
                    .setParameter("type", type);

            List<BoardVO> result = query.getResultList();
            List<BoardVO> copy = new ArrayList<>(result.size());
            for( int i = 0 ; i < result.size() ; i++ )
            {
                copy.add( result.get( i ));
            }
            Collections.reverse(copy);
            return copy;
        } catch (Exception ex) {
            em.getTransaction().rollback();
            throw ex;
        } finally {
            em.close();
        }
    }

    // insert into board (bno, title, content, contactInfo)
    // values (seq_board.nextval, #{title}, #{content}, #{contactInfo})
    @Override
    @Synchronized
    @Transactional
    public void insert(BoardVO board) {
        try{
            em.persist(board);
        } catch (Exception ex) {
            em.getTransaction().rollback();
            throw ex;
        } finally {
            em.close();
        }
    }

    // update board
    // set title = #{title},
    // location = #{location},
    // content = #{content},
    // contactInfo = #{contactInfo}
    // updateDate = sysdate
    // den = #{den},
    // bathroom = #{bathroom},
    // bedroom = #{bedroom},
    // price = #{price},
    // type = #{type},
    // memberId = #{memberId}
    // where bno = #{bno}
    @Override
    @Synchronized
    @Transactional
    public void update(BoardVO board, Long bno) {
        try {
            BoardVO target = em.find(BoardVO.class, bno);
            if (target == null) {
                throw new BoardNotFoundException();
            }
            target.setLocation(board.getLocation());
            target.setContent(board.getContent());
            target.setContactInfo(board.getContactInfo());
            target.setUpdateDate(LocalDateTime.now());
            target.setDen(board.getDen());
            target.setBathroom(board.getBathroom());
            target.setBedroom(board.getBedroom());
            target.setPrice(board.getPrice());
            target.setType(board.getType());
            target.setMemberDTO(board.getMemberDTO());
            target.setFileVOs(board.getFileVOs());
        } catch(Exception ex) {
            em.getTransaction().rollback();
            throw ex;
        } finally {
            em.close();
        }
    }

    // delete board where bno = #{bno}
    @Override
    @Synchronized
    @Transactional
    public void deleteByBNo(Long bno) {
        try {
            BoardVO board = em.find(BoardVO.class, bno);
            if (board == null) {
                throw new BoardNotFoundException();
            }
            TypedQuery<FileVO> query = em.createQuery("SELECT f FROM FileVO f WHERE f.bno = :bno", FileVO.class)
                    .setParameter("bno", bno);
            List<FileVO> files = query.getResultList();
            for(int i = 0; i < files.size(); i++) {
                em.remove(files.get(i));
            }
            em.remove(board);
        } catch (Exception ex) {
            em.getTransaction().rollback();
            throw ex;
        } finally {
            em.close();
        }
    }

    @Override
    @Synchronized
    @Transactional
    public void deleteByMemId(Long memId) {
        try {
            TypedQuery<BoardVO> queryFrist = em.createQuery("SELECT b FROM BoardVO b WHERE b.memberDTO.id = :memId", BoardVO.class)
                    .setParameter("memId", memId);
            List<BoardVO> boards = queryFrist.getResultList();
            for (BoardVO board : boards) {
                // fruit is an element of the `fruits` array.
                if (board == null) {
                    throw new BoardNotFoundException();
                }
                Long bno = board.getBno();
                TypedQuery<FileVO> querySecond = em.createQuery("SELECT f FROM FileVO f WHERE f.bno = :bno", FileVO.class)
                        .setParameter("bno", bno);
                List<FileVO> files = querySecond.getResultList();
                for (int i = 0; i < files.size(); i++) {
                    em.remove(files.get(i));
                }
                em.remove(board);
            }
        } catch (Exception ex) {
            em.getTransaction().rollback();
            throw ex;
        } finally {
            em.close();
        }
    }

    /*public List<Board> getListWithPaging(Criteria cri) {

    }

    public int getTotalCount(Criteria cri);*/

}