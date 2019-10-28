package com.project.project6.service;

import com.project.project6.member.MemberDAO;
import com.project.project6.member.MemberDTO;
import com.project.project6.message.MessageDAO;
import com.project.project6.message.MessageDTO;
import com.project.project6.request.MessageRequest;

import java.time.LocalDateTime;
import java.util.List;

public class MessageService {

    private MemberDAO memberDAO;
    private MessageDAO messageDAO;

    public void setMemberDAO(MemberDAO memberDAO) {
        this.memberDAO = memberDAO;
    }

    public void setMessageDAO(MessageDAO messageDAO) {
        this.messageDAO = messageDAO;
    }

    public void send(MessageRequest req) {
        MemberDTO senderDTO = memberDAO.getMemberById(req.getSenderId());
        MemberDTO recipientDTO = memberDAO.getMemberById(req.getRecipientId());
        MessageDTO message = new MessageDTO(req.getMessage(), senderDTO, recipientDTO, LocalDateTime.now());
        messageDAO.insert(message);
    }

    public List<MessageDTO> getMessagesBySenderId(Long sender_id) {
        List<MessageDTO> messages = messageDAO.getMessageListBySenderId(sender_id);
        return messages;
    }

    public List<MessageDTO> getMessagesByRecipientId(Long recipient_id) {
        List<MessageDTO> messages = messageDAO.getMessageListByRecipientId(recipient_id);
        return messages;
    }

    public String getRecipientName(Long recipient_id) {
        MemberDTO member = memberDAO.getMemberById(recipient_id);
        return member.getName();
    }

    public void deleteMessageByMemId(Long memId) {
        messageDAO.deleteAllMessagesByMemId(memId);
    }

}
