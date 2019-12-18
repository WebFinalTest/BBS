package com.bbs.service.impl;

import com.bbs.entity.Favorites;
import com.bbs.repository.IFavoritesRepository;
import com.bbs.service.IFavoritesService;
import com.bbs.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FavoritesServiceImpl implements IFavoritesService {

    private IFavoritesRepository favoritesRepository;

    @Autowired
    public void setFavoritesRepository(IFavoritesRepository favoritesRepository){
        this.favoritesRepository=favoritesRepository;
    }

    /*@Override
    public void createFirstFavorites(Long userId,Long favoritersId){
        Favorites favorites = new Favorites();
        favorites.setUserId(userId);
        favorites.setFavoritesId(favoritersId);
        favorites.setFavoritesName("默认收藏夹");
        favoritesRepository.createFavorites(favorites);
    }*/

    @Override
    public void createFavorites(Long userId,String favoritesName){
        Favorites favorites = new Favorites();
        Long favoritesId;
        do {
            favoritesId = Utils.randomId(11);
        }while(favoritesRepository.findFavoritesByfavoritesId(favoritesId)!=null);
        favorites.setFavoritesId(favoritesId);
        favorites.setUserId(userId);
        favorites.setFavoritesName(favoritesName);
        favoritesRepository.createFavorites(favorites);
    }

    @Override
    public void updateFavorites(Long favoritesId, String favoritesName){
        favoritesRepository.updateFavorites(favoritesId, favoritesName);
    }

    @Override
    public List<Favorites> findAllFavoritesByUserId(Long userId){
        return favoritesRepository.findAllFavoritesByUserId(userId);
    }

    @Override
    public void deleteFavoritesByFavoritesId(Long favoritesId){

        favoritesRepository.deleteFavoritesByFavoritesId(favoritesId);
    }
}
