package com.bbs.service;


import com.bbs.entity.Like;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ILikeService {
    //创建点赞
    public void createLike(Like like);
    //查看点赞过的帖子
    public List<Like> findPostLikes(@Param("userId") Long userId);
    //查看帖子点赞数
    public Long countPostLikes(@Param("postId") Long postId);
    //查看帖子是否点赞过
    public boolean isPostLike(@Param("postId") Long postId);
    //查看点赞过的评论
    public List<Like> findCommentLikes(@Param("userId") Long userId);
    //查看评论点赞数
    public Long countCommentLike(@Param("commentId") Long commentId);
    //查看评论是否点赞过
    public boolean isCommentLike(@Param("commentId") Long commentId);
    //根据帖子ID删除点赞
    public void deleteLikeByPostId(@Param("postId") Long postId,@Param("userId") Long userId);
    //根据评论ID删除点赞
    public void deleteLikeByCommentId(@Param("commentId") Long commentId,@Param("userId") Long userId);
}
