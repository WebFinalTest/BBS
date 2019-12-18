package com.bbs.service;


import com.bbs.entity.Like;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ILikeService {
    //创建点赞
    public boolean createLike(Like like);
    //查看点赞过的帖子
    public List<Like> findPostLikes(Long userId);
    //查看帖子点赞数
    public Long countPostLikes(Long postId);
    //查看帖子是否点赞过
    public boolean isPostLike(Long postId);
    //查看点赞过的评论
    public List<Like> findCommentLikes(Long userId);
    //查看评论点赞数
    public Long countCommentLike(Long commentId);
    //查看评论是否点赞过
    public boolean isCommentLike(Long commentId);
    //根据帖子ID删除点赞
    public void deleteLikeByPostId(Long postId,Long userId);
    //根据评论ID删除点赞
    public void deleteLikeByCommentId(Long commentId,Long userId);
}
