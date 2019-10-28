package com.project.project6.favourite;

import com.project.project6.member.MemberDAO;
import com.project.project6.member.MemberDTO;
import lombok.Synchronized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class FavouriteDAOJpa implements FavouriteDAO {

    @PersistenceContext
    private EntityManager em;
    @Autowired
    private MemberDAO memberDAO;

    @Override
    @Synchronized
    @Transactional
    public FavouriteDTO getBoardsIdByMemberId(Long memId) {
        try {
            TypedQuery<FavouriteDTO> query = em.createQuery("SELECT f FROM FavouriteDTO f WHERE (" +
                    "f.memberDTO.id = :memId)", FavouriteDTO.class)
                    .setParameter("memId", memId);
            FavouriteDTO result = query.getSingleResult();
            return result;
        } catch (NoResultException ex) {
            return null;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    @Synchronized
    @Transactional
    public void insertBoardId(Long memId, Long bno) {
        try{
            try{
                TypedQuery<FavouriteDTO> query = em.createQuery("SELECT f FROM FavouriteDTO f WHERE (" +
                        "f.memberDTO.id = :memId)", FavouriteDTO.class)
                        .setParameter("memId", memId);
                FavouriteDTO result = query.getSingleResult();
                result.getBnos().add(bno);
            } catch(NoResultException e) {
                MemberDTO memberDTO = memberDAO.getMemberById(memId);
                List<Long> bnos = new ArrayList<>();
                bnos.add(bno);
                FavouriteDTO favouriteDTO = new FavouriteDTO(memberDTO, bnos);
                em.persist(favouriteDTO);
            }
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
    public void deleteBoardIdByBNo(Long memId, Long bno) {
        try {
            TypedQuery<FavouriteDTO> query = em.createQuery("SELECT f FROM FavouriteDTO f WHERE (" +
                    "f.memberDTO.id = :memId)", FavouriteDTO.class)
                    .setParameter("memId", memId);

            FavouriteDTO result = query.getSingleResult();
            result.getBnos().remove(bno);
        } catch (Exception ex) {
            em.getTransaction().rollback();
            throw ex;
        } finally {
            em.close();
        }
    }

}
