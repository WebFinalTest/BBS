package com.bbs.service.impl;

import com.bbs.entity.Comment;
import com.bbs.repository.ICommentRepository;
import com.bbs.service.ICommentService;
import com.bbs.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements ICommentService {
    private ICommentRepository commentRepository;

    @Autowired
    public void setCommentRepository(ICommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public List<Comment> findByPostId(Long postId) {
        return commentRepository.findByPostId(postId);
    }

    @Override
    public List<Comment> findByFloorId(Long floorId) {
        return commentRepository.findByFloorId(floorId);
    }

    @Override
    public List<Comment> findByUserId(Long userId) {
        return commentRepository.findByUserId(userId);
    }

    @Override
    public void createComment(Comment comment) {
        Long commentId;
        do {
            commentId = Utils.randomId(12);
        }while (commentRepository.findByCommentId(commentId) != null);
        comment.setCommentId(commentId);
        commentRepository.createComment(comment);
    }

    @Override
    public void deleteByCommentId(Long commentId) {
        commentRepository.deleteByCommentId(commentId);
    }
}
