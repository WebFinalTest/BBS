package com.bbs.repository;

import com.bbs.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserRepository {
    public List<User> findAll();
    public User findById(Long id);
    public void save(User user);
    public void update(User user);
    public void deleteById(Long id);
}