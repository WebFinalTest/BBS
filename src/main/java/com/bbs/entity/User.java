package com.bbs.entity;

import lombok.Data;

@Data
public class User {
    private Long userId;
    private String userName;
    private String password;
    private String email;
    private Long userType;
    private Long points;
    private Long qq;
    private Long phone;
    private String workplace;
    private String habitation;
}
