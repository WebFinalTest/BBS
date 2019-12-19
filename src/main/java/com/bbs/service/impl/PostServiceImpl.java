package com.bbs.service.impl;

import com.bbs.entity.Collect;
import com.bbs.entity.Like;
import com.bbs.entity.Post;
import com.bbs.repository.*;
import com.bbs.service.IPostService;
import com.bbs.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PostServiceImpl implements IPostService {
    private final Long pageSize = 30L;
    private IPostRepository postRepository;
    private IUserRepository userRepository;
    private ICommentRepository commentRepository;
    private ILikeRepository likeRepository;
    private ICollectRepository collectRepository;
    private IFavoritesRepository favoritesRepository;

    @Autowired
    public void setFavoritesRepository(IFavoritesRepository favoritesRepository) {
        this.favoritesRepository = favoritesRepository;
    }

    @Autowired
    public void setLikeRepository(ILikeRepository likeRepository) {
        this.likeRepository = likeRepository;
    }

    @Autowired
    public void setCollectRepository(ICollectRepository collectRepository) {
        this.collectRepository = collectRepository;
    }

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
    @Transactional
    public boolean createPost(Post post) {
        Long postId;
        do {
            postId = Utils.randomId(10);
        }while (postRepository.findByPostId(postId) != null);
        post.setPostId(postId);
        // 如果该帖子是需求贴
        if (post.isPostType())
            postRepository.saveWithPostPoints(post);
        else {
            Long points = userRepository.findPointsByUserId(post.getUserId());
            if (points > post.getPostPoints())
                postRepository.saveWithOutPostPoints(post);
            else
                return false;
        }
        return true;
    }

    @Override
    @Transactional
    public void deletePost(Long postId) {
        Post post = postRepository.findByPostId(postId);
        if(post.isPostType() && post.getAdoptCommentId() == null) {
            userRepository.increasePointsByUserId(post.getPostPoints(),post.getUserId());
        }
        postRepository.deleteById(postId);
    }

    @Override
    public void updatePost(Post post) {
        Date date = new Date();
        post.setUpdateDate(date);
        post.setRenewDate(date);
        postRepository.update(post);
    }

    @Override
    @Transactional
    public void adoptComment(Long postId, Long commentId) {
        Post post = postRepository.findByPostId(postId);
        Long commentUserId = commentRepository.findUserIdByCommentId(commentId);
        userRepository.increasePointsByUserId(post.getPostPoints(), commentUserId);
        postRepository.adoptComment(postId, commentId);
    }

    @Override
    public void qualityPost(Long postId) {
        postRepository.changeQuality(postId, true);
    }

    @Override
    public void unQualityPost(Long postId) {
        postRepository.changeQuality(postId, false);
    }

    @Override
    public void topPost(Long postId) {
        postRepository.changeTop(postId, true);
    }

    @Override
    public void unTopPost(Long postId) {
        postRepository.changeTop(postId, false);
    }

    @Override
    public void updatePostTitle(Long postId, String postTitle) {
        Post post = postRepository.findByPostId(postId);
        post.setPostTitle(postTitle);
        Date date = new Date();
        post.setUpdateDate(date);
        post.setRenewDate(date);
        postRepository.update(post);
    }

    @Override
    public void updatePostContent(Long postId, String postContent) {
        Post post = postRepository.findByPostId(postId);
        post.setPostContent(postContent);
        Date date = new Date();
        post.setUpdateDate(date);
        post.setRenewDate(date);
        postRepository.update(post);
    }

    /* 查找方法Start */
    @Override
    public List<Post> findPosts() {
        return postRepository.findPosts();
    }

    @Override
    public List<Post> findPostsByPage(Long page) {
        return postRepository.findPostsByPage(page, pageSize);
    }

    @Override
    public List<Post> findPostsByUserId(Long userId) {
        return postRepository.findPostsByUserId(userId);
    }

    @Override
    public List<Post> findPostsByPageUserId(Long userId, Long page) {
        return postRepository.findPostsByPageUserId(userId, page, pageSize);
    }

    @Override
    public Post findPostByPostId(Long postId) {
        return postRepository.findByPostId(postId);
    }

    @Override
    public Long countPostsPage() {
        Long postsNum = postRepository.countPosts();
        return postsNum / pageSize + (postsNum % pageSize == 0 ? 0 : 1);
    }

    @Override
    public Long countPostsPageByUserId(Long userId) {
        Long postsNum = postRepository.countPostsByUserId(userId);
        return postsNum / pageSize + (postsNum % pageSize == 0 ? 0 : 1);
    }
    /* 查找方法End */

    //新加入的方法

    @Override
    @Transactional
    public List<Post> findPostsByFavoritesId(Long favoritesId, Long Page) {
        List<Collect> collects = collectRepository.findAllCollectsByFavoritesId(favoritesId);
        List<Post> posts = new ArrayList<>();
        for (Collect collect:collects) {
            posts.add(postRepository.findByPostId(collect.getPostId()));
        }
        return posts;
    }

    @Override
    public List<Post> findLikePostsByUserId(Long userId, Long Page) {
        List<Like> likes = likeRepository.findPostsByUserId(userId);
        List<Post> posts = new ArrayList<>();
        for (Like like:likes) {
            posts.add(postRepository.findByPostId(like.getPostId()));
        }
        return posts;
    }

    @Override
    public List<Post> findQualityPostsByPage(Long page) {
        return postRepository.findQualityPostsByPage(page,pageSize);
    }

    @Override
    public List<Post> findTopPostsByPage(Long page) {
        return postRepository.findTopPostsByPage(page,pageSize);
    }
}
