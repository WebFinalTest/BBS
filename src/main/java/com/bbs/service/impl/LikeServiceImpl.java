package com.bbs.service.impl;

import com.bbs.entity.Like;
import com.bbs.repository.ICommentRepository;
import com.bbs.repository.ILikeRepository;
import com.bbs.service.ILikeService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LikeServiceImpl implements ILikeService {
    private ILikeRepository iLikeRepository;
    private ICommentRepository iCommentRepository;

    @Autowired
    public void setiCommentRepository(ICommentRepository iCommentRepository) {
        this.iCommentRepository = iCommentRepository;
    }

    @Autowired
    public void setiLikeRepository(ILikeRepository iLikeRepository) {
        this.iLikeRepository = iLikeRepository;
    }

    @Override
    @Transactional
    public boolean createLike(Like like) {
        iLikeRepository.save(like);
        Like returnLike;
        if(like.getCommentId()!=null){
            returnLike = iLikeRepository.findByCommentIdAndUserId(like.getCommentId(),like.getUserId());
            iCommentRepository.likeByCommentId(like.getCommentId());
        }
        else{
            returnLike = iLikeRepository.findByPostIdAndUserId(like.getPostId(), like.getUserId());
        }
        if(returnLike!=null){
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public List<Like> findPostLikes(@Param("userId") Long userId) {
        return iLikeRepository.findPostsByUserId(userId);
    }

    @Override
    public Long countPostLikes(@Param("postId") Long postId) {
        List<Like> postLikes=iLikeRepository.findByPostId(postId);
        return new Long((long)postLikes.size());
    }

    @Override
    public boolean isPostLike(@Param("postId") Long postId) {
        List<Like> postLikes=iLikeRepository.findByPostId(postId);
        if(postLikes.size()>0){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public List<Like> findCommentLikes(@Param("userId") Long userId) {
        return iLikeRepository.findCommentsByUserId(userId);
    }

    @Override
    public Long countCommentLike(@Param("commentId") Long commentId) {
        List<Like> commentLike=iLikeRepository.findByCommentId(commentId);
        return new Long((long)commentLike.size());
    }

    @Override
    public boolean isCommentLike(@Param("commentId") Long commentId) {
        List<Like> commentLike=iLikeRepository.findByCommentId(commentId);
        if(commentLike.size()>0){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public void deleteLikeByPostId(@Param("postId") Long postId,@Param("userId") Long userId) {
        iLikeRepository.deleteByPostId(postId,userId);
    }

    @Override
    @Transactional
    public void deleteLikeByCommentId(@Param("commentId") Long commentId,@Param("userId") Long userId) {
        iLikeRepository.deleteByCommentId(commentId,userId);
        iCommentRepository.unlikeByCommentId(commentId);
    }

}

