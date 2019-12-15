package com.bbs.service;

import com.bbs.entity.User;

import java.util.List;

public interface IUserService {
    public List<User> findAll();
    public User findById(Long id);
    public void save(User user);
    public void update(User user);
    public void deleteById(Long id);
}
