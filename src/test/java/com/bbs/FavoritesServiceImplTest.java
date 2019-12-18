package com.bbs;

import com.bbs.entity.Favorites;
import com.bbs.service.IFavoritesService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FavoritesServiceImplTest {
    private IFavoritesService favoritesService;

    @Autowired
    public void setFavoritesService(IFavoritesService favoritesService) {
        this.favoritesService = favoritesService;
    }

    @Test
    public void Test(){

        favoritesService.createFavorites(891900564L,"第");

        favoritesService.updateFavorites(6L,"dsfsdfAS");

        List<Favorites> a = favoritesService.findAllFavoritesByUserId(1L);

         favoritesService.deleteFavoritesByFavoritesId(5L);

    }
}
