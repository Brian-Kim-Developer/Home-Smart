package com.project.project6.file;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.project.project6.exception.FileNotFoundException;
import lombok.Synchronized;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class FileDAOJpa implements FileDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Synchronized
    @Transactional
    public List<FileVO> getFiles() {
        try {
            TypedQuery<FileVO> query = em.createQuery("SELECT f FROM FileVO f", FileVO.class);
            List<FileVO> result = query.getResultList();
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
    public FileVO getFileByFNo(Long fno) {
        try {
            FileVO file = em.find(FileVO.class, fno);
            return file;
        } finally {
            em.close();
        }
    }

    @Override
    @Synchronized
    @Transactional
    public List<FileVO> getFilesByBno(Long bno) {
        try {
            TypedQuery<FileVO> query = em.createQuery("SELECT f FROM FileVO f WHERE f.bno = :bno", FileVO.class)
                    .setParameter("bno", bno);
            List<FileVO> result = query.getResultList();
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
    public FileVO getTitleFile(Long bno, boolean isTitle) {
        try {
            TypedQuery<FileVO> query = em.createQuery("SELECT f FROM FileVO f WHERE (" +
                    "f.bno = :bno AND " +
                    "f.isTitle = :isTitle)", FileVO.class)
                    .setParameter("bno", bno)
                    .setParameter("isTitle", isTitle);

            FileVO titleFile = query.getSingleResult();
            return titleFile;
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
    public List<FileVO> getContentFiles(Long bno, boolean isTitle) {
        try {
            TypedQuery<FileVO> query = em.createQuery("SELECT f FROM FileVO f WHERE (" +
                    "f.bno = :bno AND " +
                    "f.isTitle = :isTitle)", FileVO.class)
                    .setParameter("bno", bno)
                    .setParameter("isTitle", isTitle);

            List<FileVO> result = query.getResultList();
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
    public void register(FileVO fileVO) {
        try{
            em.persist(fileVO);
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
    public void update(FileVO fileVO) {
        try{
            FileVO target = em.find(FileVO.class, fileVO.getFno());
            if (target == null) {
                throw new FileNotFoundException();
            }
            target.setFileName(fileVO.getFileName());
            target.setFileOriName(fileVO.getFileOriName());
            target.setFileType(fileVO.getFileType());
            target.setFileData(fileVO.getFileData());
            target.setTitle(fileVO.isTitle());
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
    public void deleteByBNo(Long bno) {
        try {
            TypedQuery<FileVO> query = em.createQuery("SELECT f FROM FileVO f WHERE f.bno = :bno", FileVO.class)
                    .setParameter("bno", bno);
            List<FileVO> files = query.getResultList();
            if (files == null) {
                throw new FileNotFoundException();
            }
            for(int i = 0; i < files.size(); i++) {
                em.remove(files.get(i));
            }
        } catch (Exception ex) {
            em.getTransaction().rollback();
            throw ex;
        } finally {
            em.close();
        }
    }

}
