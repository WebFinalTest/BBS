package com.bbs.PostTest;

import com.bbs.entity.Post;
import com.bbs.service.IPostService;
import com.bbs.util.Utils;
import javafx.geometry.Pos;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IPostServiceTest {
    private IPostService postService;

    @Autowired
    public void setPostService(IPostService postService) {
        this.postService = postService;
    }

    public static char getChar(int i) {
        if (i < 10)
            return (char) ((int) '0' + i);
        else if (i < 36)
            return (char) ((int) 'a' + i - 10);
        else
            return (char) ((int) 'A' + i - 36);
    }

    public static String creatString(Long length) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < length; i++) {
            s.append(getChar((int) (Math.random() * 1000) % 62));
        }
        return s.toString();
    }

    /* 新建方法Start */
    // 测试创建帖子
    @Test
    public void createPostTest() {
        Post post = new Post();
        post.setUserId(358160797L);
        post.setPostTitle("yzh");
        post.setPostContent("zzzz");
        postService.createPost(post);
    }
    /* 新建方法End */

    /* 删除方法Start */
    // 已测试删除帖子
    @Test
    public void deletePostTest() {
        postService.deletePost(1323L);
    }
    /* 删除方法End */

    /* 修改方法Start */
    // 测试采纳评论
    @Test
    public void adoptCommentTest() {
        postService.adoptComment(1323L, 123L);
    }

    // 已测试加精帖子
    @Test
    public void qualityPostTest() {
        postService.qualityPost(1323L);
    }

    // 已测试取消加精
    @Test
    public void unQualityPostTest() {
        postService.unQualityPost(1323L);
    }

    // 已测试置顶帖子
    @Test
    public void topPostTest() {
        postService.topPost(1323L);
    }

    // 已测试取消置顶
    @Test
    public void unTopPostTest() {
        postService.unTopPost(1323L);
    }

    // 已测试修改标题
    @Test
    public void updatePostTitleTest() {
        Post post = postService.findPostByPostId(1323L);
        System.out.println(post);
        postService.updatePostTitle(1323L, creatString(Utils.randomId(2)));
        post = postService.findPostByPostId(1323L);
        System.out.println(post);
    }

    // 已测试修改帖子内容
    @Test
    public void updatePostContentTest() {
//        Post post = postService.findPostByPostId(1L);
////        System.out.println(post);
////        postService.updatePostContent(1L, creatString(Utils.randomId(3)));
////        post = postService.findPostByPostId(1L);
        Post post = new Post();
        post.setPostId(1L);
        post.setPostContent("修改成功");
        post.setPostTitle("修改成功");
        post.setPostPoints(10L);
        postService.updatePost(post);
        System.out.println(post);
    }
    /* 修改方法End */

    /* 查找方法Start */
    // 已测试查找所有帖子
    @Test
    public void findPostsTest() {
        List<Post> postList = postService.findPosts();
        List<Post> posts = postService.findPostsByPage(1L);
        List<Post> topPosts = postService.findTopPostsByPage(1L);
        if (postList == null){
            System.out.println(postList);
            System.out.println(posts);
            System.out.println(topPosts);
        }
        else
            for (Post post : postList)
                System.out.println(post);
    }

    // 已测试分页查找帖子
    @Test
    public void findPostsByPageTest() {
        List<Post> postList = postService.findPostsByPage(1L);
        if (postList.size() == 0)
            System.out.println(postList);
        else
            for (Post post : postList) {
                System.out.println(post);
            }
    }

    // 已测试查找某个用户建立的所有帖子
    @Test
    public void findPostsByUserId() {
        List<Post> postList = postService.findPostsByUserId(123321L);
        if (postList == null)
            System.out.println(postList);
        else
            for (Post post : postList) {
                System.out.println(post);
            }
    }

    // 已测试查找某个用户某页的所有帖子
    @Test
    public void findPostsByPageUserIdTest() {
        List<Post> postList = postService.findPostsByPageUserId(123321L, 1L);
        if (postList.size() == 0)
            System.out.println(postList);
        else
            for (Post post : postList) {
                System.out.println(post);
            }
    }

    // 已测试查找某个帖子
    @Test
    public void findPostByPostIdTest() {
        System.out.println(postService.findPostByPostId(1323L));
    }

    // 已测试计算所有帖子页数
    @Test
    public void countPostsPage() {
        System.out.println(postService.countPostsPage());
        System.out.println(postService.countTopPostsPage());
    }

    // 已测试计算某个用户所有帖子页数
    @Test
    public void countPostsPageByUserId() {
        System.out.println(postService.countPostsPageByUserId(123321L));
    }
    /* 查找方法End */

}
