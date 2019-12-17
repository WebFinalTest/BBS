package com.bbs.repository;

import com.bbs.entity.Post;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface IPostRepository {

    /* 新建方法 */
    public void saveWithPostPoints(Post post);

    public void saveWithOutPostPoints(Post post);

    /* 删除方法 */
    public void deleteById(Long postId);

    /* 修改方法 */
    public void changeQuality(@Param("postId") Long postId, @Param("bool") boolean bool);

    public void changeTop(@Param("postId") Long postId, @Param("bool") boolean bool);

    public void adoptComment(@Param("postId") Long postId, @Param("commentId") Long commentId);

    public void update(Post post);

    public void updateDate(@Param("updateDate") Date updateDate);

    /* 查找方法 */
    public List<Post> findPosts();

    public List<Post> findPostsByUserId(@Param("userId") Long userId);

    public Post findByPostId(@Param("postId") Long postId);
}
