package com.bbs.entity;

import lombok.Data;

@Data
public class User {
    private String userName;
    private String email;
    private String password;
    private Long type;
    private Long userId;
    private Long points;
}
