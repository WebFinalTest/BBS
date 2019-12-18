package com.bbs.service;

import com.bbs.entity.Collect;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ICollectService {
    //创建一个收藏
    public void createCollect(Collect collect);

    //更新一个收藏，把帖子放置在另一个favoritesId
    public void updateCollect(Long postId,Long userId, Long favoritesId);

    //根据收藏夹返回全部收藏
    public List<Collect> findAllCollectsByFavoritesId( Long favoritesId);

    //删除一个收藏postId,userId
    public void deleteCollect(Long postId,Long userId);
}
