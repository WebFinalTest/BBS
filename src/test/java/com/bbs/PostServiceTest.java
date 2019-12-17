package com.bbs;

import com.bbs.entity.Post;
import com.bbs.service.IPostService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostServiceTest {
    private IPostService postService;

    @Autowired
    public void setPostService(IPostService postService) {
        this.postService = postService;
    }

    /* 新建方法Start */
    // 已测试创建帖子
    @Test
    public void createPostTest() {
        Post post = new Post();
        post.setPostId(1111L);
        post.setUserId(111111111L);
        post.setPostTitle("yzh");
        post.setPostContent("zzzz");
        post.setPostType(false);
        System.out.println(postService.createPost(post));
    }
    /* 新建方法End */

    /* 删除方法Start */
    // 测试删除帖子
    @Test
    public void deletePostTest() {
        System.out.println(postService.deletePost(1323L));
    }
    /* 删除方法End */

    /* 修改方法Start */
    // 已测试加精帖子
    @Test
    public void qualityPostTest() {
        System.out.println(postService.qualityPost(1323L));
    }

    // 已测试取消加精
    @Test
    public void unQualityPostTest() {
        System.out.println(postService.unQualityPost(1323L));
    }

    // 已测试置顶帖子
    @Test
    public void topPostTest() {
        System.out.println(postService.topPost(1323L));
    }

    // 已测试取消置顶
    @Test
    public void unTopPostTest() {
        System.out.println(postService.unTopPost(1323L));
    }

    // 测试采纳评论
    @Test
    public void adoptCommentTest() {
        System.out.println(postService.adoptComment(1323L, 123L));
    }

    // 测试修改标题
    @Test
    public void updatePostTitleTest() {
        System.out.println(postService.updatePostTitle(1323L, "(^_^)／早上好"));
    }

    // 测试修改帖子内容
    @Test
    public void updatePostContentTest() {
        System.out.println(postService.updatePostContent(1323L, "你好呀everybody!你们好鸭"));
    }
    /* 修改方法End */

    /* 查找方法Start */
    // 测试查找所有帖子
    @Test
    public void findPostsTest() {
        List<Post> postList = postService.findPosts();
        System.out.println(postList);
    }

    // 测试查找某个帖子
    @Test
    public void findPostByPostIdTest() {
        Post post = postService.findPostByPostId(1323L);
        System.out.println(post);
    }
    /* 查找方法End */

}
