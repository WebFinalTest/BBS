package com.bbs.service;

import com.bbs.entity.Comment;

import java.util.List;

public interface ICommentService{
    //返回一个帖子的某页的评论
    public List<Comment> findByPostId(Long postId,Long page);

    //返回一个帖子评论页数
    public Long countCommentPagesByPostId(Long postId);

    //返回一个层级某页的评论
    public List<Comment> findByFloorId(Long floorId,Long page);

    //返回一个层级评论页数
    public Long countCommentPagesByFloorId(Long floorId);

    //返回一个用户某页的评论
    public List<Comment> findByUserId(Long userId,Long page);

    //返回一个用户评论页数
    public Long countCommentPagesByUserId(Long userId);

    //创建一个评论
    public void createComment(Comment comment);

    //删除一个评论
    public void deleteByCommentId(Long commentId);
}
