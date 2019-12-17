package com.bbs.service.impl;

import com.bbs.entity.Post;
import com.bbs.entity.User;
import com.bbs.repository.IPostRepository;
import com.bbs.repository.IUserRepository;
import com.bbs.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PostServiceImpl implements IPostService {
    private IPostRepository iPostRepository;
    private IUserRepository iUserRepository;

    @Autowired
    public PostServiceImpl(IPostRepository iPostRepository) {
        this.iPostRepository = iPostRepository;
    }

    @Override
    public boolean createPost(Post post) {
        User user = iUserRepository.findById(post.getUserId());
        if (user == null)
            return false;
        // 如果该帖子是需求贴
        if (post.isPostType())
            iPostRepository.saveWithPostPoints(post);
        else
            iPostRepository.saveWithOutPostPoints(post);
        Post post1 = iPostRepository.findByPostId(post.getPostId());
        return post1 != null;
    }

    @Override
    public boolean deletePost(Long postId) {
        Post post = iPostRepository.findByPostId(postId);
        if (post != null)
            iPostRepository.deleteById(postId);
        else
            return false;
        post = iPostRepository.findByPostId(postId);
        return post == null;
    }

    @Override
    public boolean adoptComment(Long postId, Long commentId) {
        Post post = iPostRepository.findByPostId(postId);
        if (post.isPostType()) {
            if (post.getAdoptCommentId() == null)
                iPostRepository.adoptComment(postId, commentId);
            else
                return false;
        } else {
            return false;
        }
        post = iPostRepository.findByPostId(postId);
        return post.isPostType();
    }

    @Override
    public boolean qualityPost(Long postId) {
        Post post = iPostRepository.findByPostId(postId);
        if (post == null)
            return false;
        iPostRepository.changeQuality(postId, true);
        post = iPostRepository.findByPostId(postId);
        return post.isQuality();
    }

    @Override
    public boolean unQualityPost(Long postId) {
        Post post = iPostRepository.findByPostId(postId);
        if (post == null)
            return false;
        iPostRepository.changeTop(postId, false);
        post = iPostRepository.findByPostId(postId);
        return !post.isTop();
    }

    @Override
    public boolean topPost(Long postId) {
        Post post = iPostRepository.findByPostId(postId);
        if (post == null)
            return false;
        iPostRepository.changeTop(postId, true);
        post = iPostRepository.findByPostId(postId);
        return post.isTop();
    }

    @Override
    public boolean unTopPost(Long postId) {
        Post post = iPostRepository.findByPostId(postId);
        if (post == null)
            return false;
        iPostRepository.changeTop(postId, false);
        post = iPostRepository.findByPostId(postId);
        return !post.isTop();
    }

    @Override
    public boolean updatePostTitle(Long postId, String postTitle) {
        Post post = iPostRepository.findByPostId(postId);
        if (post == null)
            return false;
        post.setPostTitle(postTitle);
        Date date = new Date();
        post.setUpdateDate(date);
        post.setRenewDate(date);
        iPostRepository.update(post);
        post = iPostRepository.findByPostId(postId);
        if (post == null)
            return false;
        return post.getPostTitle().equals(postTitle);
    }

    @Override
    public boolean updatePostContent(Long postId, String postContent) {
        Post post = iPostRepository.findByPostId(postId);
        if (post == null)
            return false;
        post.setPostContent(postContent);
        Date date = new Date();
        post.setUpdateDate(date);
        post.setRenewDate(date);
        iPostRepository.update(post);
        post = iPostRepository.findByPostId(postId);
        if (post == null)
            return false;
        return post.getPostContent().equals(postContent);
    }

    @Override
    public List<Post> findPosts() {
        return iPostRepository.findPosts();
    }

    @Override
    public List<Post> findPostsByUserId(Long userId) {
        return iPostRepository.findPostsByUserId(userId);
    }

    @Override
    public Post findPostByPostId(Long postId) {
        return iPostRepository.findByPostId(postId);
    }
}

