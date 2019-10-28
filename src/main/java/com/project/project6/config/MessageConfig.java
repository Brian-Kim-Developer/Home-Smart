package com.project.project6.config;

import com.project.project6.member.MemberDAO;
import com.project.project6.message.MessageDAO;
import com.project.project6.message.MessageDAOJpa;
import com.project.project6.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageConfig {

    @Autowired
    MemberDAO memberDAO;

    @Bean
    public MessageDAO messageDAO() {
        return new MessageDAOJpa();
    }

    @Bean
    public MessageService messageService() {
        MessageService messageService = new MessageService();
        messageService.setMemberDAO(memberDAO);
        messageService.setMessageDAO(messageDAO());
        return messageService;
    }

}
