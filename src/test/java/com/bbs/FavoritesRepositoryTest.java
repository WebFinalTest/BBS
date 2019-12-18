package com.bbs;

import com.bbs.entity.Favorites;
import com.bbs.repository.IFavoritesRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FavoritesRepositoryTest {
    private IFavoritesRepository favoritesRepository;

    @Autowired
    public void setFavoritesRepository(IFavoritesRepository favoritesRepository) {
        this.favoritesRepository = favoritesRepository;
    }

    @Test
    public void Test(){
        //存放两个文件夹
        Favorites favorites = new Favorites();
        favorites.setFavoritesId((long) 32);
        favorites.setFavoritesName("xxxsxx");
        favorites.setUserId((long) 1);

        Favorites favorites1 = new Favorites();
        favorites1.setFavoritesId((long) 3432);
        favorites1.setFavoritesName("xxdrxx");
        favorites1.setUserId((long) 1);

        favoritesRepository.createFavorites(favorites);

        favoritesRepository.createFavorites(favorites1);

        //读取userId为3的文件夹
        List<Favorites> list = favoritesRepository.findAllFavoritesByUserId( 3L);

        //更新
        favoritesRepository.updateFavorites((long) 2545,"asdf");

        //删除文件夹
        favoritesRepository.deleteFavoritesByFavoritesId((long) 213213);

    }
}
