package com.bbs.service;

import com.bbs.entity.Comment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ICommentService{
    //返回一个帖子的所有评论
    public List<Comment> findByPostId(@Param("postId") Long postId);

    //返回一个层级所有的评论
    public List<Comment> findByFloorId(@Param("floorId") Long floorId);

    //返回一个用户的所有评论
    public List<Comment> findByUserId(@Param("userId") Long userId);

    //创建一个评论
    public void createComment(Comment comment);

    //删除一个评论
    public void deleteByCommentId(@Param("commentId") Long commentId);
}
