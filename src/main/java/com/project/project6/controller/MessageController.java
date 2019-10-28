package com.project.project6.controller;

import com.project.project6.message.MessageDTO;
import com.project.project6.request.MessageRequest;
import com.project.project6.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/message")
public class MessageController {

    private MessageService messageService;

    @Autowired
    public void setMessageService(MessageService messageService) {
        this.messageService = messageService;
    }

    @RequestMapping("/step1")
    public String sendMessageStep1(@RequestParam("senderId") Long sender_id, @RequestParam("recipientId") Long recipient_id, Model model) {
        String recipient_name = messageService.getRecipientName(recipient_id);
        model.addAttribute("recipientName", recipient_name);
        model.addAttribute("senderId", sender_id);
        model.addAttribute("recipientId", recipient_id);
        return "message/step1";
    }

    @RequestMapping("/step2")
    public String sendMessageStep2(MessageRequest req) {
        messageService.send(req);
        return "message/step2";
    }

    @RequestMapping("/sent")
    public String viewSentMessages(@RequestParam("senderId") Long sender_id, Model model) {
        System.out.println("This is senderid" + sender_id);
        List<MessageDTO> messages = messageService.getMessagesBySenderId(sender_id);
        model.addAttribute("messages", messages);
        return "message/sent";
    }

    @RequestMapping("/received")
    public String viewReceivedMessages(@RequestParam("recipientId") Long recipient_id, Model model) {
        List<MessageDTO> messages = messageService.getMessagesByRecipientId(recipient_id);
        model.addAttribute("messages", messages);
        return "message/received";
    }

}
