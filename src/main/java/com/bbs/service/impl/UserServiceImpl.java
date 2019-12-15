package com.bbs.service.impl;

import com.bbs.entity.User;
import com.bbs.repository.IUserRepository;
import com.bbs.service.IUserService;
import com.bbs.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {
    private IUserRepository iUserRepository;

    @Autowired
    public UserServiceImpl(IUserRepository iUserRepository) {
        this.iUserRepository = iUserRepository;
    }

    @Override
    public List<User> findAll() {
        return iUserRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return iUserRepository.findById(id);
    }

    @Override
    @Transactional
    public void save(User user) {
        iUserRepository.deleteById(194915045L);
        Long id;
        do {
            id = Utils.randomId(9);
        }while (iUserRepository.findById(id) != null);
        user.setUserId(id);
        iUserRepository.save(user);
    }

    @Override
    @Transactional
    public void update(User user) {
        iUserRepository.update(user);
    }

    @Override
    public void deleteById(Long id) {
        iUserRepository.deleteById(id);
    }
}
