package com.bbs.repository;

import com.bbs.entity.Post;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface IPostRepository {

    /* 新建方法 */
    // 新建需求
    public void saveWithPostPoints(Post post);

    // 新建普通帖子
    public void saveWithOutPostPoints(Post post);

    /* 删除方法 */
    // 删除帖子
    public void deleteById(Long postId);

    /* 修改方法 */
    // 修改帖子加精属性
    public void changeQuality(@Param("postId") Long postId, @Param("bool") boolean bool);

    // 修改帖子置顶属性
    public void changeTop(@Param("postId") Long postId, @Param("bool") boolean bool);

    // 采纳帖子
    public void adoptComment(@Param("postId") Long postId, @Param("commentId") Long commentId);

    // 更新帖子
    public void update(Post post);

    // 修改更新时间
    public void renewDate(@Param("postId") Long postId, @Param("renewDate") Date renewDate);

    /* 查找方法 */
    // 查找所有帖子
    public List<Post> findPosts();

    // 查找某一页所有帖子
    public List<Post> findPostsByPage(@Param("page") Long page, @Param("pageSize") Long pageSize);

    //查找所有帖子
    public List<Post> findPostsByUserId(@Param("userId") Long userId);

    // 查找某个用户某一页所有帖子
    public List<Post> findPostsByPageUserId(@Param("userId") Long userId, @Param("page") Long page, @Param("pageSize") Long pageSize);

    // 查找单条帖子
    public Post findByPostId(@Param("postId") Long postId);

    // 计算帖子总数
    public Long countPosts();

    // 计算某个用户所建帖子总数
    public Long countPostsByUserId(@Param("userId") Long userId);

    //对帖子点赞
    public void likeByPostId(@Param("postId") Long postId);

    //取消对帖子的点赞
    public void unlikeByPostId(@Param("postId") Long postId);

}
