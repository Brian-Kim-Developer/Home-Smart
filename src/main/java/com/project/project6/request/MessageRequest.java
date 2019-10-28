package com.project.project6.request;

import lombok.Data;

@Data
public class MessageRequest {

    private String message;
    private Long senderId;
    private Long recipientId;

    public MessageRequest() {}

}
