package com.bbs.service.impl;

import com.bbs.entity.Comment;
import com.bbs.repository.ICommentRepository;
import com.bbs.repository.IPostRepository;
import com.bbs.repository.IUserRepository;
import com.bbs.service.ICommentService;
import com.bbs.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements ICommentService {
    private ICommentRepository commentRepository;
    private IUserRepository userRepository;
    private IPostRepository postRepository;
    private final Long pageSize = 20L;

    @Autowired
    public void setPostRepository(IPostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Autowired
    public void setUserRepository(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setCommentRepository(ICommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public Long countCommentPagesByPostId(Long postId) {
        Long pages = commentRepository.countCommentByPostId(postId);
        return pages % pageSize == 0 ? pages / pageSize : pages / pageSize + 1;
    }

    @Override
    public List<Comment> findByFloorId(Long floorId, Long page) {
        return commentRepository.findByFloorId(floorId, page, pageSize);
    }

    @Override
    public Long countCommentPagesByFloorId(Long floorId) {
        Long pages = commentRepository.countCommentByFloorId(floorId);
        return pages % pageSize == 0 ? pages / pageSize : pages / pageSize + 1;
    }

    @Override
    public List<Comment> findByUserId(Long userId, Long page) {
        return commentRepository.findByUserId(userId, page, pageSize);
    }

    @Override
    public Long countCommentPagesByUserId(Long userId) {
        Long pages = commentRepository.countCommentByUserId(userId);
        return pages % pageSize == 0 ? pages / pageSize : pages / pageSize + 1;
    }

    @Override
    public List<Comment> findByPostId(Long postId,Long page) {
        return commentRepository.findByPostId(postId,page,pageSize);
    }

    @Override
    @Transactional
    public void createComment(Comment comment) {
        Long commentId;
        do {
            commentId = Utils.randomId(12);
        }while (commentRepository.findByCommentId(commentId) != null);

        //增加10积分
        userRepository.increasePointsByUserId(10L,comment.getUserId());

        comment.setCommentId(commentId);
        commentRepository.createComment(comment);

        //更新帖子更新时间
        postRepository.renewDate(comment.getPostId(),new Date());
    }

    @Override
    public void deleteByCommentId(Long commentId) {
        commentRepository.deleteByCommentId(commentId);
    }
}
