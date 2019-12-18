package com.bbs.service;

import com.bbs.entity.Favorites;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IFavoritesService {

    //创建第一个收藏夹
    //public void createFirstFavorites(@Param("userId")Long userId,@Param("favoritersId")Long favoritersId);

    //创建一个收藏夹
    public void createFavorites(Long userId,String favoritesName);

    //更新一个收藏夹,改收藏夹名字
    public void updateFavorites(Long favoritesId,String favoritesName);

    //根据userId返回该Id全部收藏夹
    public List<Favorites> findAllFavoritesByUserId(Long userId);

    //根据收藏夹Id删除收藏夹
    public void deleteFavoritesByFavoritesId(Long favoritesId);
}
