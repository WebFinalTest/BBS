package com.bbs.service.impl;

import com.bbs.entity.User;
import com.bbs.repository.IUserRepository;
import com.bbs.service.IUserService;
import com.bbs.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {
    private IUserRepository userRepository;

    @Autowired
    public UserServiceImpl(IUserRepository userRepository) {
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
        Long userId;
        do {
            userId = Utils.randomId(9);
        }while (userRepository.findByUserId(userId) != null);
        user.setUserId(userId);
        userRepository.save(user);
        return user;
    }

    @Override
    public void update(User user) {
        userRepository.update(user);
    }

}
