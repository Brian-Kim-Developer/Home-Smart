package com.project.project6.request;

import lombok.Data;

@Data
public class BoardSearchRequest {

    private int den;
    private int bathroom;
    private int bedroom;
    private int min;
    private int max;
    private String type;

    public BoardSearchRequest() {}

}
