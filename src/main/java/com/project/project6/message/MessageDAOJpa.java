package com.project.project6.message;

import lombok.Synchronized;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MessageDAOJpa implements MessageDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Synchronized
    @Transactional
    // select * from message where recipient_id = #{recipient_id};
    public List<MessageDTO> getMessageListByRecipientId(Long recipient_id) {
        try {
            TypedQuery<MessageDTO> query = em.createQuery("SELECT m FROM MessageDTO m WHERE m.recipientDTO.id = :recipient_id", MessageDTO.class)
                    .setParameter("recipient_id", recipient_id);
            List<MessageDTO> result = query.getResultList();
            List<MessageDTO> copy = new ArrayList<>(result.size());
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
    // select * from message where sender_id = #{sender_id};
    public List<MessageDTO> getMessageListBySenderId(Long sender_id) {
        try {
            TypedQuery<MessageDTO> query = em.createQuery("SELECT m FROM MessageDTO m WHERE m.senderDTO.id = :sender_id", MessageDTO.class)
                    .setParameter("sender_id", sender_id);
            List<MessageDTO> result = query.getResultList();
            List<MessageDTO> copy = new ArrayList<>(result.size());
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
    // insert into board (ID, sender_id, recipient_id, message)
    // values (seq_board.nextval, #{sender_id}, #{recipient_id}, #{message})
    public void insert(MessageDTO message) {
        try{
            em.persist(message);
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
    // delete * from message where sender_id = #{sender_id};
    public void deleteAllMessagesByMemId(Long memId){
        try{
            TypedQuery<MessageDTO> queryFirst = em.createQuery("SELECT m FROM MessageDTO m WHERE m.senderDTO.id = :memId", MessageDTO.class)
                    .setParameter("memId", memId);
            List<MessageDTO> messagesSent = queryFirst.getResultList();
            for(int i = 0; i < messagesSent.size(); i++) {
                em.remove(messagesSent.get(i));
            }
            TypedQuery<MessageDTO> querySecond = em.createQuery("SELECT m FROM MessageDTO m WHERE m.recipientDTO.id = :memId", MessageDTO.class)
                    .setParameter("memId", memId);
            List<MessageDTO> messagesReceived = querySecond.getResultList();
            for(int i = 0; i < messagesReceived.size(); i++) {
                em.remove(messagesReceived.get(i));
            }
        } catch (Exception ex) {
            em.getTransaction().rollback();
            throw ex;
        } finally {
            em.close();
        }
    }

}
