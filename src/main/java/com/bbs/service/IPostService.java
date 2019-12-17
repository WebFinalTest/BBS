package com.bbs.service;

import com.bbs.entity.Post;

import java.util.List;

public interface IPostService {

    /* 新建方法Start */
    // 创建会议
    public boolean createPost(Post post);
    /* 新建方法End */

    /* 删除方法Start */
    // 删除帖子
    public boolean deletePost(Long postId);
    /* 删除方法End */

    /* 修改方法Start */
    // 加精帖子
    public boolean qualityPost(Long postId);

    // 取消加精
    public boolean unQualityPost(Long postId);

    // 置顶帖子
    public boolean topPost(Long postId);

    // 取消置顶
    public boolean unTopPost(Long postId);

    // 采纳评论
    public boolean adoptComment(Long postId, Long commentId);

    // 修改标题
    public boolean updatePostTitle(Long postId, String postTitle);

    // 修改帖子内容
    public boolean updatePostContent(Long postId, String postContent);
    /* 修改方法End */

    /* 查找方法Start */
    // 查找所有帖子
    public List<Post> findPosts();

    // 查找某个用户建立的所有帖子
    public List<Post> findPostsByUserId(Long userId);

    // 查找某个帖子
    public Post findPostByPostId(Long postId);

//    // 查看帖子的点赞数
//    public Long getLikesByPostId(Long postId);
//
//    // 查看帖子的收藏数
//    public Long getCollectsByPostId(Long postId);
    /* 查找方法End */
}
