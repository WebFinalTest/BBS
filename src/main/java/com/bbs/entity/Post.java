package com.bbs.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Post {
    private Long postId;
    private Long userId;
    private String postTitle;
    private String postContent;
    private Date createDate;
    private Date updateDate;
    private Date renewDate;
    private boolean top;
    private boolean quality;
    private boolean postType;
    private Long postPoints;
    private Long adoptCommentId;
    private Long likes;
    private Long collects;
}
