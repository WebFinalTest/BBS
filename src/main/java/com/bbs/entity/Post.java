package com.bbs.entity;

import lombok.Data;

@Data
public class Post {
    private Long postId;
    private Long userId;
    private String postTitle;
    private String postContent;
    private String createDate;
    private String updateDate;
    private String renewDate;
    private boolean top;
    private boolean quality;
    private boolean postType;
    private Long postPoints;
    private Long adoptCommentId;
    private Long likes;
    private Long collects;
}
