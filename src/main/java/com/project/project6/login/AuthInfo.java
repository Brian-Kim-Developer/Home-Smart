package com.project.project6.login;

import java.io.Serializable;

public class AuthInfo implements Serializable {

    private Long id;
    private String email;
    private String name;

    public AuthInfo(Long id, String email, String name) {
        this.id = id;
        this.email = email;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

}