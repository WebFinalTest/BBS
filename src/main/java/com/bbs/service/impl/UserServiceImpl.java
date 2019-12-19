package com.bbs.service.impl;

import com.bbs.entity.Favorites;
import com.bbs.entity.User;
import com.bbs.repository.IFavoritesRepository;
import com.bbs.repository.IUserRepository;
import com.bbs.service.IUserService;
import com.bbs.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {
    private IUserRepository userRepository;
    private IFavoritesRepository favoritesRepository;

    @Autowired
    public void setFavoritesRepository(IFavoritesRepository favoritesRepository) {
        this.favoritesRepository = favoritesRepository;
    }

    @Autowired
    public void setUserRepository(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findByUserId(Long userId) {
        return userRepository.findByUserId(userId);
    }

    @Override
    public User login(String email, String password) {
        return userRepository.login(email,password);
    }

    @Override
    public boolean isUsedByEmail(String email) {
        return userRepository.findByEmail(email) != null;
    }

    @Override
    public boolean isUsedByUserName(String userName) {
        return userRepository.findByUserName(userName) != null;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }


    @Override
    public User register(User user) {
        Long userId,favoritesId;
        Favorites favorites = new Favorites();
        do {
            userId = Utils.randomId(9);
        }while (userRepository.findByUserId(userId) != null);
        user.setUserId(userId);
        userRepository.save(user);
        do {
             favoritesId = Utils.randomId(11);
        }while (favoritesRepository.findFavoritesByfavoritesId(favoritesId) != null);
        favorites.setFavoritesId(favoritesId);
        favorites.setUserId(userId);
        favorites.setFavoritesName("默认收藏夹");
        favoritesRepository.createFavorites(favorites);
        return user;
    }

    @Override
    @Transactional
    public boolean updatePasswordByUserId(Long userId, String originalPassword, String newPassword) {
        User user = userRepository.findByUserId(userId);
        if(user == null || !user.getPassword().equals(originalPassword))
            return false;
        userRepository.updatePasswordByUserId(userId,originalPassword,newPassword);
        user = userRepository.findByUserId(userId);
        return (user != null && user.getPassword().equals(newPassword));
    }

    @Override
    public void update(User user) {
        userRepository.update(user);
    }

}
