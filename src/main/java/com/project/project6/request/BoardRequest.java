package com.project.project6.request;

import lombok.Data;

@Data
public class BoardRequest {

    private String location;
    private String content;
    private String contactInfo;
    private int den;
    private int bathroom;
    private int bedroom;
    private int price;
    private String type;
    private Long memberID;

    public BoardRequest() {}

}
