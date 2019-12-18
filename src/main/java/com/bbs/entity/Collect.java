package com.bbs.entity;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Collect {
    private Long postId;
    private Long userId;
    private Timestamp collectDate;
    private Long favoritesId;

}
