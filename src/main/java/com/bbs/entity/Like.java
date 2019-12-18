package com.bbs.entity;

import lombok.Data;

@Data
public class Like {
    private Long postId;
    private Long commentId;
    private Long userId;
}
