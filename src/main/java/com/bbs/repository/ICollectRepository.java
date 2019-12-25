package com.bbs.repository;

import com.bbs.entity.Collect;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICollectRepository {

    //创建一个收藏
    public void createCollect(Collect collect);

    //更新一个收藏，把帖子放置在另一个favoritesId
    public void updateCollect(@Param("postId") Long postId, @Param("userId") Long userId, @Param("favoritesId") Long favoritesId);

    //根据收藏夹返回全部收藏
    public List<Collect> findAllCollectsByFavoritesId(@Param("favoritesId") Long favoritesId);

    //删除一个收藏
    public void deleteCollect(@Param("postId") Long postId, @Param("userId") Long userId);

    //查看是否收藏过
    public Boolean isCollectPostByUserId(@Param("postId") Long postId, @Param("userId") Long userId);
}
