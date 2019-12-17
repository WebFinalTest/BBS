package com.bbs.service;

import com.bbs.entity.Comment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ICommentService{
    //返回一个帖子的某页的评论
    public List<Comment> findByPostId(Long postId,Long page,Long pageSize);

    //返回一个帖子评论页数
    public Long countCommentPagesByPostId(Long postId,Long pageSize);

    //返回一个层级某页的评论
    public List<Comment> findByFloorId(Long floorId,Long page,Long pageSize);

    //返回一个层级评论页数
    public Long countCommentPagesByFloorId(Long floorId,Long pageSize);

    //返回一个用户某页的评论
    public List<Comment> findByUserId(Long userId,Long page,Long pageSize);

    //返回一个用户评论页数
    public Long countCommentPagesByUserId(Long userId,Long pageSize);

    //创建一个评论
    public void createComment(Comment comment);

    //删除一个评论
    public void deleteByCommentId(Long commentId);
}
