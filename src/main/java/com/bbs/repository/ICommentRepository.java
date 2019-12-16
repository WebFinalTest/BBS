package com.bbs.repository;

import com.bbs.entity.Comment;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICommentRepository {
    //返回一个帖子的所有评论
    public List<Comment> findByPostId(@Param("postId") Long postId);

    //返回一个层级所有的评论
    public List<Comment> findByFloorId(@Param("floorId") Long floorId);

    //返回一个用户的所有评论
    public List<Comment> findByUserId(@Param("userId") Long userId);

    //按评论ID返回一个评论
    public Comment findByCommentId(@Param("commentId") Long commentId);

    //创建一个评论
    public void createComment(Comment comment);

    //删除一个评论
    public void deleteByCommentId(@Param("commentId") Long commentId);

    //赞评论
    public void likeByCommentId(@Param("commentId") Long commentId);

    //取消赞评论
    public void unlikeByCommentId(@Param("commentId") Long commentId);

}
