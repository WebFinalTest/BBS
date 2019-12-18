package com.bbs.repository;

import com.bbs.entity.Favorites;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IFavoritesRepository {
    //创建一个收藏夹
    public void createFavorites(Favorites favorites);

    //更新一个收藏夹,改收藏夹名字
    public void updateFavorites(@Param("favoritesId") Long favoritesId, @Param("favoritesName") String favoritesName);

    //根据userId返回该Id全部收藏夹
    public List<Favorites> findAllFavoritesByUserId(@Param("userId") Long userId);

    //根据favoritesId返回该收藏夹
    public Favorites findFavoritesByfavoritesId(@Param("favoritesId") Long favoritesId);

    //根据收藏夹Id删除收藏夹
    public void deleteFavoritesByFavoritesId(@Param("favoritesId") Long favoritesId);
}
