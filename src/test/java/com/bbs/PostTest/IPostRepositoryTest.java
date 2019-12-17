package com.bbs.PostTest;

import com.bbs.entity.Post;
import com.bbs.repository.IPostRepository;
import com.bbs.util.Utils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IPostRepositoryTest {
    private IPostRepository postRepository;

    @Autowired
    public void setPostRepository(IPostRepository postRepository) {
        this.postRepository = postRepository;
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

    // 测试新建需求
    @Test
    public void saveWithPostPointsTest() {
        Post post = new Post();
        Long postId;
        do {
            postId = Utils.randomId(9);
        } while (postRepository.findByPostId(postId) != null);
        post.setPostId(postId);
        post.setUserId(123321L);
        post.setPostPoints(200L);
        post.setPostType(true);
        post.setPostTitle(creatString(Utils.randomId(1)));
        post.setPostContent(creatString(Utils.randomId(2)));
        postRepository.saveWithPostPoints(post);
    }

    // 已测试新建普通帖子
    @Test
    public void saveWithOutPostPointsTest() {
        Post post = new Post();
        Long postId;
        do {
            postId = Utils.randomId(9);
        } while (postRepository.findByPostId(postId) != null);
        post.setPostId(postId);
        post.setUserId(123321L);
        post.setPostType(false);
        post.setPostTitle(creatString(Utils.randomId(1)));
        post.setPostContent(creatString(Utils.randomId(2)));
        postRepository.saveWithOutPostPoints(post);
    }

    /* 删除方法 */
    // 已测试删除帖子
    @Test
    public void deleteByIdTest() {
        postRepository.deleteById(417177894L);
        System.out.println(postRepository.findByPostId(417177894L));
    }

    /* 修改方法 */
    // 已测试修改帖子加精属性
    @Test
    public void changeQualityTest() {
        postRepository.changeQuality(419706810L, true);
        System.out.println(postRepository.findByPostId(419706810L).isQuality());
        postRepository.changeQuality(419706810L, false);
        System.out.println(postRepository.findByPostId(419706810L).isQuality());
    }

    // 已测试修改帖子置顶属性
    @Test
    public void changeTopTest() {
        postRepository.changeTop(419706810L, true);
        System.out.println(postRepository.findByPostId(419706810L).isTop());
        postRepository.changeTop(419706810L, false);
        System.out.println(postRepository.findByPostId(419706810L).isTop());
    }

    // 测试采纳帖子
    @Test
    public void adoptCommentTest() {
        postRepository.adoptComment(419706810L, 4197L);
        System.out.println(postRepository.findByPostId(419706810L).getAdoptCommentId());
    }

    // 已测试更新帖子
    @Test
    public void updateTest() {
        Post post = postRepository.findByPostId(264272525L);
        System.out.println(post);
        post.setPostTitle(creatString(Utils.randomId(1)));
        post.setPostContent(creatString(Utils.randomId(3)));
        Date currentDate = new Date();
        post.setUpdateDate(currentDate);
        post.setRenewDate(currentDate);
        post.setTop(true);
        post.setQuality(false);
        postRepository.update(post);
        post = postRepository.findByPostId(264272525L);
        System.out.println(post);
    }

    // 已测试修改更新日期
    @Test
    public void renewDateTest() {
        Post post = postRepository.findByPostId(264272525L);
        System.out.println(post);
        Date date = new Date();
        postRepository.renewDate(post.getPostId(), date);
        post = postRepository.findByPostId(264272525L);
        System.out.println(post);
    }

    /* 查找方法 */
    // 已测试查找所有帖子
    @Test
    public void findPostsTest() {
        List<Post> postList = postRepository.findPosts();
        if (postList == null)
            System.out.println(postList);
        else
            for (Post post : postList) {
                System.out.println(post);
            }
    }

    // 测试查找某一页所有帖子
    @Test
    public void findPostsByPageTest() {
        List<Post> postList = postRepository.findPostsByPage(3L, 2L);
        if (postList.size() == 0)
            System.out.println(postList);
        else
            for (Post post : postList) {
                System.out.println(post);
            }
    }

    // 已测试查找某个用户某一页所有帖子
    @Test
    public void findPostsByUserIdTest() {
        List<Post> postList = postRepository.findPostsByPageUserId(123321L, 3L, 2L);
        if (postList.size() == 0)
            System.out.println(postList);
        else
            for (Post post : postList) {
                System.out.println(post);
            }
    }

    // 已测试查找单条帖子
    @Test
    public void findByPostIdTest() {
        System.out.println(postRepository.findByPostId(1111L));
    }

    // 测试计算帖子总数
    @Test
    public void countPostsTest() {
        System.out.print(postRepository.countPosts());
    }

    // 测试计算某个用户所建帖子总数
    @Test
    public void countPostsByUserIdTest() {
        System.out.print(postRepository.countPostsByUserId(123321L));
    }

}
