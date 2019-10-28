package com.project.project6.member;

import java.util.List;

import com.project.project6.exception.MemberNotFoundException;
import com.project.project6.service.BoardDeleteService;
import com.project.project6.service.MemberDeleteService;
import lombok.Synchronized;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.transaction.annotation.Transactional;

public class MemberDAOJpa implements MemberDAO {

    @PersistenceContext
    private EntityManager em;

    // select * from MEMBER  where EMAIL = ?
    @Override
    @Synchronized
    @Transactional
    public MemberDTO getMemberByEmail(String email) {
        try {
            TypedQuery<MemberDTO> query =
                    em.createQuery("SELECT m FROM MemberDTO m WHERE m.email = :email", MemberDTO.class).setParameter("email", email);
            MemberDTO member = query.getSingleResult();
            if (member == null) {
                System.out.println("This is empty!!!!!!");
            }
            return member;
        } catch (NoResultException ex) {
            System.out.println("this no result exception");
            return null;
        }
            finally {
            em.close();
        }
    }

    // insert into MEMBER (EMAIL, PASSWORD, NAME, REGDATE) values (?, ?, ?, ?)
    @Override
    @Synchronized
    @Transactional
    public void insert(MemberDTO member) {
        try {
            em.persist(member);
        } catch (Exception ex) {
            em.getTransaction().rollback();
            throw ex;
        } finally {
            em.close();
        }
    }

    // update MEMBER set NAME = ?, PASSWORD = ? where EMAIL = ?
    @Override
    @Synchronized
    @Transactional
    public void update(MemberDTO member) {
        try {
            MemberDTO target = em.find(MemberDTO.class, member.getId());
            if (target == null) {
                throw new MemberNotFoundException();
            }
            target.setEmail(member.getEmail());
            target.setPassword(member.getPassword());
            target.setName(member.getName());
        } catch(Exception ex) {
            em.getTransaction().rollback();
            throw ex;
        } finally {
            em.close();
        }
    }

    // select * from MEMBER
    @Override
    @Synchronized
    @Transactional
    public List<MemberDTO> getMemberList() {
        try {
            TypedQuery<MemberDTO> query = em.createQuery("SELECT m FROM MemberDTO m WHERE m.id > 0", MemberDTO.class);
            List<MemberDTO> result = query.getResultList();
            return result;
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
    public MemberDTO getMemberById(Long memId) {
        try {
            MemberDTO member = em.find(MemberDTO.class, memId);
            return member;
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
    public void deleteMemberById(Long memId) {
        try {
            MemberDTO member = em.find(MemberDTO.class, memId);
            em.remove(member);
        } catch (Exception ex) {
            em.getTransaction().rollback();
            throw ex;
        } finally {
            em.close();
        }
    }

}