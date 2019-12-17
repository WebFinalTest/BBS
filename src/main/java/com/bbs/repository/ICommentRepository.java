package com.bbs.repository;

import com.bbs.entity.Comment;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICommentRepository {
    //返回一个帖子的某页的评论
    public List<Comment> findByPostId(@Param("postId") Long postId,@Param("page") Long page,@Param("pageSize") Long pageSize);

    //返回一个帖子评论总数
    public Long countCommentByPostId(@Param("postId") Long postId);

    //返回一个层级的某页的评论
    public List<Comment> findByFloorId(@Param("floorId") Long floorId,@Param("page") Long page,@Param("pageSize") Long pageSize);

    //返回一个层级评论总数
    public Long countCommentByFloorId(@Param("floorId") Long floorId);

    //返回一个用户的某页的评论
    public List<Comment> findByUserId(@Param("userId") Long userId,@Param("page") Long page,@Param("pageSize") Long pageSize);

    //返回一个用户评论数
    public Long countCommentByUserId(@Param("userId") Long userId);

    //按评论ID返回一个评论
    public Comment findByCommentId(@Param("commentId") Long commentId);

    //按评论ID返回一个UserId
    public Long findUserIdByCommentId(@Param("commentId") Long commentId);

    //创建一个评论
    public void createComment(Comment comment);

    //删除一个评论
    public void deleteByCommentId(@Param("commentId") Long commentId);

    //赞评论
    public void likeByCommentId(@Param("commentId") Long commentId);

    //取消赞评论
    public void unlikeByCommentId(@Param("commentId") Long commentId);

}
