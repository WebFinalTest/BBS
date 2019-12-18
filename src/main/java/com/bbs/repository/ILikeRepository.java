package com.bbs.repository;

import com.bbs.entity.Like;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ILikeRepository {
    public List<Like> findAll();
    public List<Like> findByUserId(@Param("userId") Long userId);
    public List<Like> findByPostId(@Param("postId") Long postId);
    public List<Like> findByCommentId(@Param("commentId") Long commentId);
    public Like findByPostIdAndUserId(@Param("postId") Long postId,@Param("userId") Long userId);
    public Like findByCommentIdAndUserId(@Param("commentId") Long commentId,@Param("userId") Long userId);
    public void save(Like like);
    public void update(Like like);
    public void deleteByPostId(@Param("postId") Long postId,@Param("userId") Long userId);
    public void deleteByCommentId(@Param("commentId") Long commentId,@Param("userId") Long userId);
}