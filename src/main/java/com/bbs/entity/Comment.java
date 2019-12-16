package com.bbs.entity;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Comment {
    private Long commentId;
    private Long postId;
    private Long userId;
    private String commentContent;
    private Long replyId;
    private Long floorId;
    private Timestamp createDate;
    private Long likes;
}
